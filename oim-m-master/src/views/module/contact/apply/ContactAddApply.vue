<template>
    <v-dialog
        v-model="show"
    >
        <v-card>
            <div v-if="!isBlocked">
                <v-card-title>
                    添加联系人
                </v-card-title>
                <v-card-text>
                    <v-text-field
                        v-model="apply.remark"
                        placeholder="备注名"
                        required
                    ></v-text-field>
                    <v-select
                        v-model="apply.categoryId"
                        :items="categoryList"
                        label="分组"
                        item-value="id"
                        item-text="name"
                        outlined
                    ></v-select>
                </v-card-text>

                <div v-if="'3'===verifyType">
                    <v-card>
                        <v-card-title>回答问题</v-card-title>
                        <v-card-text>
                            <div>
                                <span>问题:</span>
                                <label>
                                    {{ apply.question }}
                                </label>
                            </div>

                            <div>
                                <v-text-field
                                    required
                                    v-model="apply.answer"
                                    placeholder="答案">
                                </v-text-field>
                            </div>
                        </v-card-text>
                    </v-card>
                </div>

                <div v-if="'4'===verifyType">
                    <v-card>
                        <v-card-title>回答问题</v-card-title>
                        <v-card-text>
                            <div v-for="(item, index) in answerList" :key="index">
                                <div>
                                    <span>问题:</span>
                                    <label>
                                        {{ item.question }}
                                    </label>
                                </div>
                                <div>
                                    <v-text-field
                                        v-model="item.answer"
                                        placeholder="答案"
                                        required
                                    ></v-text-field>
                                </div>
                            </div>
                        </v-card-text>
                    </v-card>
                </div>
            </div>
            <div v-if="isBlocked">
                对方拒绝添加好友！
            </div>
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
                    v-if="!isBlocked" @click="sendAddRequest"
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
import ContactCategoryBox from '@/app/com/main/module/business/contact/box/ContactCategoryBox';


@Component({
    components: {},
})
export default class ContactAddApply extends Vue {
    private show: boolean = false;
    private userId: string = '';
    private apply: ContactAddApplyData = new ContactAddApplyData();
    private answerList: ContactVerifyAnswer[] = [];
    private categoryList: ContactCategory[] = [];
    private verifyType: string = '0';
    private isBlocked: boolean = false;

    public mounted() {
        //
    }

    public setShow(show: boolean): void {
        this.show = show;
    }

    public setUserId(userId: string) {
        this.userId = userId;
        this.initialize();
        this.loadSetting(userId);
    }

    private initialize(): void {
        this.apply = new ContactAddApplyData();
        this.answerList = [];
        this.verifyType = '0';

        const contactListBox: ContactCategoryBox = app.appContext.getMaterial(ContactCategoryBox);
        this.categoryList = contactListBox.getCategoryList();
        if (this.categoryList.length > 0) {
            const category = this.categoryList[0];
            this.apply.categoryId = category.id;
        }
    }

    private loadSetting(userId: string) {

        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success && data.body) {
                            const questionList: ContactVerifyQuestion[] = data.body.questions;
                            const verifySetting: ContactVerifySettingData = data.body.setting;
                            own.setSetting(verifySetting, questionList);
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
        const contactController: ContactController = app.appContext.getMaterial(ContactController);
        contactController.getContactAddVerifySetting(userId, back);
    }

    private setSetting(verifySetting: ContactVerifySettingData, questionList: ContactVerifyQuestion[]) {

        if (verifySetting) {
            this.verifyType = verifySetting.verifyType;
            this.apply.question = verifySetting.question;
        }

        if (questionList) {
            for (const q of questionList) {
                const a: ContactVerifyAnswer = new ContactVerifyAnswer();
                a.questionId = q.id;
                a.question = q.question;
                this.answerList.push(a);
            }
        }
    }

    private sendAddRequest() {
        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            own.setShow(false);
                        } else {
                            Prompt.message(info, '', '');
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

        const apply: ContactAddApplyData = this.apply;
        const answerList: ContactVerifyAnswer[] = this.answerList;
        const contactController: ContactController = app.appContext.getMaterial(ContactController);
        const personalBox: PersonalBox = app.appContext.getMaterial(PersonalBox);
        apply.targetUserId = this.userId;
        apply.applyUserId = personalBox.getUserId();
        contactController.sendAddRequest(apply, answerList, back);
    }
}
</script>

<style scoped>

</style>
