<template>
    <div class="chat-pane">
        <v-card
            class="mx-auto"
            elevation="8"
            dark
            color="primary"
        >
            <div>
                <v-list-item
                    @click=""
                >
                    <back-button></back-button>
                    <v-toolbar-title>{{ model.info.name }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-menu offset-y>
                        <template v-slot:activator="{ on }">
                            <v-btn
                                v-on="on"
                                icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                        </template>
                        <v-list>
                            <v-list-item
                                @click="openHistory"
                            >
                                <v-list-item-title>聊天记录</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </v-list-item>
            </div>
        </v-card>
        <div class="center">
            <ReadPane :data="readMapper"
                      :items="model.messageData.list"
                      @on-scroll="handleScroll"
                      @on-refresh="onRefresh"
            ></ReadPane>
        </div>
        <div v-if='model.messageData.promptShow&&!model.atInfo.show' tabindex="-1">
            <div class="prompt-message" @click="toMessageKeyView(model.messageData.promptKey)">
                <div class="prompt-message-inner">
                    {{ model.messageData.promptText }}
                </div>
            </div>
        </div>
        <div v-if='model.atInfo.show' tabindex="-1">
            <div class="prompt-message" @click="toMessageKeyView(model.atInfo.messageKey)">
                <div class="prompt-message-inner">
                    {{ model.atInfo.chatUserName }}@我：{{ model.atInfo.chatText }}
                </div>
            </div>
        </div>
        <div class="bottom">
            <write-pane
                :data="writeMapper"
                @on-send="send"
                @on-key-press='onKeyPress'>

            </write-pane>
            <!--                <span class="btn-mic"><i :class="'icon icon-' "></i></span>-->
            <!--            <input v-model="text" class="message-input" @keyup.enter="send"/>-->
            <!--            <v-btn class="button-send" small @click="send">{{'发送'}}</v-btn>-->
        </div>

    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';
import WritePane from '@/views/component/chat/WritePane.vue';
import ReadPane from '@/views/component/chat/ReadPane.vue';

import groupChatViewModel from '@/platform/vue/view/model/GroupChatViewModel';
import app from '@/app/App';
import ContentBuildUtil from '@/platform/wap/util/ContentBuildUtil';
import Prompt from '@/platform/wap/common/Prompt';
import BaseUtil from '@/app/lib/util/BaseUtil';
import ReadMapper from '@/views/component/chat/ReadMapper';
import WriteMapper from '@/views/component/chat/WriteMapper';
import ChatWriteViewEntity from '@/platform/vue/view/entity/ChatWriteViewEntity';
import ChatReadViewEntityDefaultImpl from '@/platform/vue/view/entity/impl/ChatReadViewEntityDefaultImpl';
import ChatWriteViewEntityDefaultImpl from '@/platform/vue/view/entity/impl/ChatWriteViewEntityDefaultImpl';
import ChatReadViewEntity from '@/platform/vue/view/entity/ChatReadViewEntity';
import Content from '@/app/com/common/chat/Content';
import CoreContentUtil from '@/app/com/common/chat/util/CoreContentUtil';
import RouterUtil from '@/common/vue/RouterUtil';
import PromptType from '@/app/com/client/define/prompt/PromptType';

@Component({
    components: {
        BackButton,
        ReadPane,
        WritePane,
    },
})
export default class GroupChatPane extends Vue {

    private model = groupChatViewModel;
    private readMapper: ReadMapper = new ReadMapper();
    private writeMapper: WriteMapper = new WriteMapper();

    private text: string = '';
    private isLoading: boolean = false;

    public mounted() {
        this.initialize();

        const own = this;

        const model = this.model;
        const readMapper = this.readMapper;
        const writeMapper = this.writeMapper;

        const readViewEntity: ChatReadViewEntity = {
            setScrollTop(size: number) {
                readMapper.setScrollTop(size);
            },
            getScrollHeight(): number {
                return readMapper.getScrollHeight();
            },
            updateScrollIntoView(viewId: string): void {
                readMapper.updateScrollIntoView(viewId);
            },
        } as ChatReadViewEntityDefaultImpl;
        const writeViewEntity: ChatWriteViewEntity = {
            setInnerHTML(html: string) {
                writeMapper.setInnerHTML(html);
            },
            getInnerHTML() {
                return writeMapper.getInnerHTML();
            },
        } as ChatWriteViewEntityDefaultImpl;
        model.setReadViewEntity(readViewEntity);
        model.setWriteViewEntity(writeViewEntity);
        model.setOnKeyChange((key: string) => {
            // no
        });
    }

    private initialize() {
        const own = this;
    }

    private onKeyPress() {
        const own = this;
        const model = this.model;
        const writeMapper = this.writeMapper;
        model.viewData.data.html = writeMapper.getInnerHTML();
    }


    private onRefresh() {
        const own = this;
        own.loadHistory();
    }

    private handleScroll(info: { event: Event, scrollHeight: number, scrollTop: number, scrollPosition: string }) {
        const own = this;
        const model = this.model;
        if (info) {
            model.viewData.data.scrollHeight = info.scrollHeight;
            model.viewData.data.scrollTop = info.scrollTop;
            model.viewData.data.scrollPosition = info.scrollPosition;
        }
    }

    private loadHistory() {
        groupChatViewModel.loadHistory();
    }

    private toMessageKeyView(messageKey: string) {
        this.model.atInfo.show = false;
        if (messageKey) {
            this.readMapper.updateScrollIntoView(messageKey);
        }
    }


    public openHistory() {
        RouterUtil.toByPath('/chat.group.history');
    }

    private send(content: Content) {
        const model = this.model;
        const data = this;
        if (content) {
            const text = CoreContentUtil.getText(content);
            const itemSize = CoreContentUtil.getItemSize(content);
            if (text.length > 10000 || itemSize > 1000) {
                app.prompt('内容过长！', '警告', PromptType.warn);
            }
            if (itemSize === 0) {
                data.writeMapper.setInnerHTML('');
                data.writeMapper.keepCursorLastIndex();
                model.viewData.data.html = '';
            } else {
                model.send(content, (success, message) => {
                    if (!success) {
                        app.prompt(message, '警告', PromptType.warn);
                    } else {
                        data.writeMapper.setInnerHTML('');
                        data.writeMapper.keepCursorLastIndex();
                        model.viewData.data.html = '';
                    }
                });
            }
        }
    }
}
</script>

<style lang="scss">
.prompt-message {
    background-color: #d7d7d7;
    z-index: 1024;
    cursor: pointer;
    position: relative;
    top: 0;
    right: 0;
    left: 0;
}
</style>
