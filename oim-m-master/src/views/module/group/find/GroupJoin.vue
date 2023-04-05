<template>
    <v-dialog
        v-model="show"
    >
        <v-card>
            <div v-if="!isBlocked">
                <v-card-title>
                    加入群
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

                <v-card-text class="text--primary">
                    <div v-if="'3'===joinType">
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
                                    <v-text-field required v-model="apply.answer" placeholder="答案"></v-text-field>
                                </div>
                            </v-card-text>
                        </v-card>
                    </div>

                    <div v-if="'4'===joinType">
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
                                            required
                                            v-model="item.answer"
                                            placeholder="答案">
                                        </v-text-field>
                                    </div>
                                </div>
                            </v-card-text>
                        </v-card>
                    </div>
                </v-card-text>

            </div>
            <div v-if="isBlocked">
                拒绝加入！
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

import GroupCategory from '@/app/com/main/module/business/group/bean/GroupCategory';
import GroupRelationBox from '@/app/com/main/module/business/group/box/GroupRelationBox';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import PersonalBox from '@/app/com/main/module/business/personal/box/PersonalBox';
import GroupJoinApplyData from '@/app/com/main/module/business/group/data/GroupJoinApplyData';
import GroupJoinVerifyAnswer from '@/app/com/main/module/business/group/bean/GroupJoinVerifyAnswer';
import GroupJoinSetting from '@/app/com/main/module/business/group/bean/GroupJoinSetting';
import GroupJoinVerifyQuestion from '@/app/com/main/module/business/group/bean/GroupJoinVerifyQuestion';
import GroupSettingController from '@/app/com/main/module/business/group/controller/GroupSettingController';
import GroupJoinController from '@/app/com/main/module/business/group/controller/GroupJoinController';
import GroupCategoryBox from '@/app/com/main/module/business/group/box/GroupCategoryBox';


@Component({
    components: {},
})
export default class GroupJoinPane extends Vue {
    private show: boolean = false;
    private groupId: string = '';
    private apply: GroupJoinApplyData = new GroupJoinApplyData();
    private answerList: GroupJoinVerifyAnswer[] = [];
    private categoryList: GroupCategory[] = [];
    private joinType: string = '0';
    private isBlocked: boolean = false;

    public mounted() {
        //
    }

    public setShow(show: boolean): void {
        this.show = show;
    }

    public setGroupId(groupId: string) {
        this.groupId = groupId;
        this.initialize();
        this.loadSetting(groupId);
    }

    private initialize(): void {
        this.apply = new GroupJoinApplyData();
        this.answerList = [];
        this.joinType = '0';

        const groupListBox: GroupCategoryBox = app.appContext.getMaterial(GroupCategoryBox);
        this.categoryList = groupListBox.getCategoryList();
        if (this.categoryList.length > 0) {
            const category = this.categoryList[0];
            this.apply.categoryId = category.id;
        }
    }

    private loadSetting(groupId: string) {

        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success && data.body) {
                            const questionList: GroupJoinVerifyQuestion[] = data.body.questions;
                            const joinSetting: GroupJoinSetting = data.body.setting;
                            own.setSetting(joinSetting, questionList);
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
        const groupSettingController: GroupSettingController = app.appContext.getMaterial(GroupSettingController);
        groupSettingController.getJoinSetting(groupId, back);
    }

    private setSetting(joinSetting: GroupJoinSetting, questionList: GroupJoinVerifyQuestion[]) {

        if (joinSetting) {
            this.joinType = joinSetting.joinType;
            this.apply.question = joinSetting.question;
        }

        if (questionList) {
            for (const q of questionList) {
                const a: GroupJoinVerifyAnswer = new GroupJoinVerifyAnswer();
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

        const apply: GroupJoinApplyData = this.apply;
        const answerList: GroupJoinVerifyAnswer[] = this.answerList;
        const groupJoinController: GroupJoinController = app.appContext.getMaterial(GroupJoinController);
        const personalBox: PersonalBox = app.appContext.getMaterial(PersonalBox);
        apply.groupId = this.groupId;
        apply.applyUserId = personalBox.getUserId();
        groupJoinController.joinApply(apply, answerList, back);
    }
}
</script>

<style scoped>

</style>
