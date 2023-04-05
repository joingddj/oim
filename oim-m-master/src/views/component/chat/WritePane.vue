<template>
    <div>
        <div class="input-edit-top">
            <div class="top">
                <pre ref='inputArea' class="message-input" @paste.capture.prevent=""
                     @keypress="onKeypress"
                     @keyup="onKeyup"
                     contenteditable="true"/>
                <div class="icon-button">
                    <v-btn @click="send" icon>
                        <v-icon title="发送">mdi-send</v-icon>
                    </v-btn>
                    <v-btn @click="openFace" icon>
                        <v-icon title="表情">mdi-face</v-icon>
                    </v-btn>
                    <v-btn icon>
                        <van-uploader :after-read="afterRead" capture="">
                            <v-icon title="发送">mdi-folder-image</v-icon>
                        </van-uploader>
                    </v-btn>
                </div>
            </div>
        </div>
        <div class="input-edit-bottom">
            <slot v-show="!showFace"></slot>
            <v-lazy>
                <face-pane v-show="showFace" @on-selected="onFaceSelected"></face-pane>
            </v-lazy>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';


import app from '@/app/App';
import {ServerType, Protocol} from '@/app/common/config/constant/ServerConstant';

import BaseUtil from '@/app/lib/util/BaseUtil';
import WriteMapper from '@/views/component/chat/WriteMapper';
import PasteHandlerUtil from '@/common/web/util/PasteHandlerUtil';
import DragHandlerUtil from '@/common/web/util/DragHandlerUtil';
import Content from '@/app/com/common/chat/Content';
import WebFileSupportUtil from '@/common/web/util/WebFileSupportUtil';
import FileNameUtil from '@/app/common/util/FileNameUtil';
import Item from '@/app/com/common/chat/Item';
import FileValue from '@/app/com/common/chat/item/FileValue';
import Section from '@/app/com/common/chat/Section';
import ContentUploadFileService from '@/app/com/main/module/support/file/service/ContentUploadFileService';
import ImageValue from '@/app/com/common/chat/item/ImageValue';
import ImageToFileUtil from '@/common/web/util/ImageToFileUtil';
import WebContentAnalysisUtil from '@/common/web/util/WebContentAnalysisUtil';
import FileSeverApi from '@/app/com/main/module/support/file/constant/FileSeverApi';
import ServerController from '@/app/com/main/module/business/server/controller/ServerController';
import ContentUploadImageService from '@/app/com/main/module/support/file/service/ContentUploadImageService';
import UploadResult from '@/app/com/main/module/support/file/data/UploadResult';
import DocumentUtil from '@/common/web/util/DocumentUtil';
import FacePane from '@/views/main/face/FacePane.vue';
import FaceImageUtil from '@/common/web/common/face/FaceImageUtil';
import FaceItem from '@/app/com/main/module/support/face/data/FaceItem';


@Component({
    components: {
        FacePane,
    },
})
export default class WritePane extends Vue {

    private uploadInfo = {
        fileAction: '',
        fileDisabled: false,
        imageAction: '',
        imageDisabled: false,
    };

    @Prop({
        type: WriteMapper,
        required: false,
        default: () => (new WriteMapper()),
    })
    private data!: WriteMapper;

    private showFace = false;

    public mounted() {
        this.initialize();
    }

    public insertHtmlAtCursor(html: string) {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        DocumentUtil.insertAtCursor(inputArea as Element, html);
    }

    public setInnerHTML(html: string) {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        (inputArea as Element).innerHTML = html;
    }

    public getInnerHTML(): string {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        return (inputArea as Element).innerHTML;
    }

    private initialize() {
        const own = this;
        // todo
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        if (inputArea instanceof Element) {
            const inputAreaElement = inputArea as Element;
            (inputArea as Element).addEventListener('paste', (e: Event) => {
                PasteHandlerUtil.handle(e,
                    (html: string) => {
                        own.insertHtmlAtCursor(html);
                    },
                    (file: File) => {
                        own.insertImage(file);
                    }, (files: File[]) => {
                        own.uploadFiles(files);
                    });
            });

            inputAreaElement.addEventListener('drop', (e: Event) => {
                DragHandlerUtil.handle(e,
                    (html: string) => {
                        own.insertHtmlAtCursor(html);
                    },
                    (file: File) => {
                        own.insertImage(file);
                    }, (files: File[]) => {
                        own.uploadFiles(files);
                    });
            }, false);
            inputAreaElement.addEventListener('dragleave', (e) => {
                e.stopPropagation();
                e.preventDefault();
            });
            inputAreaElement.addEventListener('dragenter', (e) => {
                e.stopPropagation();
                e.preventDefault();
            });
            inputAreaElement.addEventListener('dragover', (e) => {
                e.stopPropagation();
                e.preventDefault();
            });
            this.data.setElement(inputAreaElement);
            // inputAreaElement.addEventListener('',(e:KeyboardEvent)=>{})
        }

        const serverController: ServerController = app.appContext.getMaterial(ServerController);
        const address = serverController.getAddress(ServerType.file, Protocol.HTTP);
        if (!address || !address.enabled) {
            this.uploadInfo.fileDisabled = true;
            this.uploadInfo.imageDisabled = true;
        } else {
            const fileHttp = address.address + FileSeverApi.FILE_UPLOAD;
            const imageHttp = address.address + FileSeverApi.IMAGE_UPLOAD;
            this.uploadInfo.fileDisabled = false;
            this.uploadInfo.imageDisabled = false;
            this.uploadInfo.fileAction = fileHttp;
            this.uploadInfo.imageAction = imageHttp;
        }
    }

    private onKeypress(e: KeyboardEvent) {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        this.keypress(e, inputArea as Element);
        if (!e.shiftKey && e.keyCode === 13) {
            e.returnValue = false;
            this.send();
            return false;
        }
    }

    private onKeyup(e: KeyboardEvent) {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        this.doOnKeyup(e, inputArea as Element);
    }

    private onInput(e: InputEvent) {
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        this.doOnInput(e, inputArea as Element);
    }

    private showFacePane(e: Event) {
        const facePaneName = 'facePane';
        const facePane: any = this.$refs[facePaneName];
        facePane.setShow(true);
    }

    private onFaceSelected(face: FaceItem) {
        if (face) {
            const html = FaceImageUtil.createFaceImageHtml(face);
            this.insertHtmlAtCursor(html);
        }
    }

    private send() {
        const own = this;
        const inputAreaPaneName = 'inputArea';
        const inputArea = this.$refs[inputAreaPaneName];
        if (inputArea) {
            const area = inputArea as any;
            const childNodes = area.childNodes;
            if (childNodes) {
                const content = WebContentAnalysisUtil.getContent(childNodes);
                if (content) {
                    own.onSend(content);
                }
            }
        }
    }

    private afterRead(fileInfo: any) {
        if (fileInfo && fileInfo.file) {
            this.uploadImage(fileInfo.file);
        }
    }

    private insertImage(file: File): void {
        const own = this;
        ImageToFileUtil.imageFile2Base64(file, (base64) => {
            const html = '<img style="max-width: 60%" src="' + base64 + '"  />';
            own.insertHtmlAtCursor(html);
        });
    }

    private uploadImage(file: File): void {
        const own = this;
        const key = '1.png';
        const map: Map<string, File> = new Map<string, File>();
        map.set(key, file);
        const cuis: ContentUploadImageService = app.appContext.getMaterial(ContentUploadImageService);
        cuis.uploadImages(map, (success: boolean, rm: Map<string, UploadResult>, message?: string) => {
            if (success) {
                const ur = rm.get(key);
                if (ur && ur.result && ur.result.body) {
                    const type = file.type;

                    const data = ur.result.body;
                    const id = data.id;
                    const name = data.name;
                    const size = data.size;
                    const url = data.url;

                    const iv: ImageValue = new ImageValue();
                    iv.id = id;
                    iv.name = name;
                    iv.url = url;
                    iv.size = size;
                    iv.type = type;
                    iv.extension = FileNameUtil.getSuffixName(name);

                    const value = BaseUtil.objectToJson(iv);
                    const html = '<img style="max-width: 60%" src="' + url + '" value=\'' + value + '\' />';
                    own.insertHtmlAtCursor(html);
                }
            }
        });
    }


    private uploadFiles(files: File[]): void {
        if (files) {
            //
        }
    }

    private uploadFile(file: File): void {
        const own = this;
        const key = '1.png';
        const map: Map<string, File> = new Map<string, File>();
        map.set(key, file);
        const uploadFileService: ContentUploadFileService = app.appContext.getMaterial(ContentUploadFileService);
        uploadFileService.uploadFile(file, (success, uploadResult, message) => {
            if (uploadResult && uploadResult.result && uploadResult.result.body) {
                const data = uploadResult.result;
                own.handleFileSend(data, file);
            }
        });
    }

    private handleFileSend(data: any, file: File) {
        const own = this;
        if (data && data.body) {

            const type = file.type;

            const fileData = data.body;
            const id = fileData.id;
            const name = fileData.name;
            const size = fileData.size;
            const url = fileData.url;
            const path = fileData.path;


            const content: Content = new Content();
            const section: Section = new Section();
            content.sections.push(section);


            const item: Item = new Item();
            item.type = Item.TYPE_FILE;

            const iv: FileValue = new FileValue();
            iv.id = id;
            iv.name = name;
            iv.size = size;
            iv.url = url;
            iv.type = type;
            iv.extension = FileNameUtil.getSuffixName(name);

            item.value = iv;
            section.items.push(item);

            const isVideo = WebFileSupportUtil.isSupportVideoByName(name);
            const isAudio = WebFileSupportUtil.isSupportAudioByName(name);
            const isImage = WebFileSupportUtil.isSupportImageByName(name);
            if (isVideo) {
                iv.url = path;
                item.type = Item.TYPE_VIDEO;
            } else if (isAudio) {
                iv.url = path;
                item.type = Item.TYPE_AUDIO;
            } else if (isImage) {
                iv.url = path;
                item.type = Item.TYPE_IMAGE;
            }
            own.onFile(content);
        }
    }

    private openFace() {
        this.showFace = !this.showFace;
    }

    @Emit('on-send')
    private onSend(content: Content) {
        // no
    }

    @Emit('on-key-press')
    private keypress(evt: KeyboardEvent, e: Element) {
        // no
    }

    @Emit('on-key-up')
    private doOnKeyup(evt: KeyboardEvent, e: Element) {
        // no
    }

    @Emit('on-input')
    private doOnInput(evt: InputEvent, e: Element) {
        // no
    }

    @Emit('on-file-content')
    private onFile(content: Content) {
        // no
    }
}

</script>

<style lang="less" scoped>

.input-edit-top {
    .top {
        display: flex;
        justify-content: center;
        padding: 5px 10px;
    }

    .icon-button {
        display: inline-block;
        margin-left: 12px;
        vertical-align: top;
    }
}

.input-edit-middle {

}

.input-edit-bottom {

}

.message-input-area {
    display: inline;
    min-height: 48px;
    width: 100%;
    border-top: 1px solid #d6d6d6;
    position: absolute;
    right: 0;
    /*bottom: 0;*/
    left: 0;
    background-color: #eee;
}

.message-input {
    width: calc(100% - 130px);
    /*height: 30px;*/
    //position: absolute;
    top: 9px;
    /*bottom: 9px;*/
    left: 50px;
    outline: none;
    border: 1px solid;
    border-radius: 5px;
    color: #aaa;
    margin: 0;
    padding: 0;
    overflow-y: auto;
    max-height: 150px;
    min-height: 30px;
}

.button-send {
    position: absolute;
    right: 60px;
    top: 8px;
    width: 60px;
}

.image-send {
    position: absolute;
    right: 8px;
    top: 2px;
    width: 42px;
}
</style>
