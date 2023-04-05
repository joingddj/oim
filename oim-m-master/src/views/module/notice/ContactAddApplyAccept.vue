<template>
    <v-dialog
        v-model="show"
    >
        <v-card>
            <v-card-title>
                联系人
            </v-card-title>
            <v-card-text>
                <v-text-field
                    v-model="handle.remark"
                    placeholder="备注名"
                    required
                ></v-text-field>
                <v-select
                    v-model="handle.categoryId"
                    :items="categoryList"
                    label="分组"
                    item-value="id"
                    item-text="name"
                    outlined
                ></v-select>
            </v-card-text>
            <v-card-actions>
                <v-btn
                    text
                    @click="show = false"
                >
                    取消
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="sendAddResponse"
                >
                    确定
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import app from '@/app/App';
import ContactVerifyAnswer from '@/app/com/main/module/business/contact/data/ContactVerifyAnswer';
import ContactAddApplyData from '@/app/com/main/module/business/contact/data/ContactAddApplyData';
import ContactCategory from '@/app/com/main/module/business/contact/bean/ContactCategory';
import ContactRelationBox from '@/app/com/main/module/business/contact/box/ContactRelationBox';
import ContactController from '@/app/com/main/module/business/contact/controller/ContactController';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import ContactVerifySettingData from '@/app/com/main/module/business/contact/data/ContactVerifySettingData';
import ContactVerifyQuestion from '@/app/com/main/module/business/contact/data/ContactVerifyQuestion';
import PersonalBox from '@/app/com/main/module/business/personal/box/PersonalBox';
import ContactAddHandleData from '@/app/com/main/module/business/contact/data/ContactAddHandleData';
import ContactAddApply from '@/app/com/main/module/business/contact/bean/ContactAddApply';
import ContactCategoryBox from '@/app/com/main/module/business/contact/box/ContactCategoryBox';


@Component({
    components: {},
})
export default class ContactAddApplyAccept extends Vue {
    private show: boolean = false;
    private applyId: string = '';
    private handle: ContactAddHandleData = new ContactAddHandleData();
    private categoryList: ContactCategory[] = [];
    private onBack: any;

    public mounted() {
        // do nothing
    }

    public setShow(show: boolean): void {
        this.show = show;
    }

    public setApplyId(applyId: string, onBack?: any) {
        this.applyId = applyId;
        this.onBack = onBack;
        this.initialize();
    }

    private initialize(): void {
        this.handle = new ContactAddHandleData();
        const contactListBox: ContactCategoryBox = app.appContext.getMaterial(ContactCategoryBox);
        this.categoryList = contactListBox.getCategoryList();
        if (this.categoryList.length > 0) {
            const category = this.categoryList[0];
            this.handle.categoryId = category.id;
        }
    }

    private sendAddResponse() {
        const onBack = this.onBack;
        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            own.setShow(false);
                            if (typeof onBack === 'function') {
                                onBack(own.handle.handleType);
                            }
                        }
                    }
                }
            },
            lost(data: any): void {
                Prompt.notice('请求失败！');
            },
            timeOut(data: any): void {
                Prompt.notice('请求超时！');
            },
        } as DataBackAction;

        const handle: ContactAddHandleData = this.handle;
        const contactController: ContactController = app.appContext.getMaterial(ContactController);
        const personalBox: PersonalBox = app.appContext.getMaterial(PersonalBox);
        handle.handleType = '1';
        handle.applyIds.push(this.applyId);
        contactController.sendAddResponse(handle, back);
    }
}
</script>

<style scoped>

</style>
