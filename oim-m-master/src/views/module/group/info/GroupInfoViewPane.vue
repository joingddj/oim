<template>
    <div class="common-main-scroll-pane">
        <v-card
            class="mx-auto"
            elevation="8"
            dark
        >
            <v-img
                height="145"
                max-height="145"
                dark
                :src="infoBg"
            >
                <div class="user-info-card-top">
                    <v-list-item
                        @click=""
                    >
                        <back-button></back-button>
                        <v-toolbar-title>{{ '群信息' }}</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-list-item>
                </div>
            </v-img>
        </v-card>

        <div class="user-info-card-head">
            <div>
                <v-avatar size="80">
                    <v-img :src="model.group.avatar"></v-img>
                </v-avatar>
            </div>
            <div class="user-info-card-name-text">
                <div><h2>{{ model.group.name }}</h2></div>
            </div>
        </div>
        <div class="user-info-card-detail">
            <v-card>
                <v-list>
                    <v-list-item
                        @click=""
                        two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '备注' }}</v-list-item-title>
                            <v-list-item-subtitle>
                                {{ model.relation.remark }}
                                <a v-if="model.isJoin" @click="openRemark">
                                    <i class="fa fa-edit" aria-hidden="true"></i>
                                </a>
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                        @click=""
                        two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '群号' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.group.number }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                        @click=""
                        two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '介绍' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.group.introduce }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item link @click="openMemberList">
                        <v-list-item-title>查看成员</v-list-item-title>
                        <v-icon>mdi-chevron-right</v-icon>
                    </v-list-item>
                </v-list>
            </v-card>
            <v-divider class="mb-4"></v-divider>
            <v-card>
                <v-col>
                    <v-btn
                        ref="button"
                        block
                        color="primary"
                        @click="openSend"
                    >
                        发送消息
                    </v-btn>
                </v-col>
            </v-card>
        </div>

        <v-dialog
            v-model="openDialog"
            max-width="500px"
        >
            <v-card>
                <v-card-title>
                    修改备注
                </v-card-title>
                <v-card-text>
                    <v-text-field
                        v-model="remark"
                        :placeholder="'备注名'"
                        required
                    ></v-text-field>
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        text
                        @click="openDialog = false"
                    >
                        取消
                    </v-btn>
                    <v-btn
                        color="primary"
                        text
                        @click="updateRemark"
                    >
                        确定
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';


import app from '@/app/App';
import User from '@/app/com/main/module/business/user/bean/User';
import DataBackAction from '@/app/base/net/DataBackAction';
import GroupRelationController from '@/app/com/main/module/business/group/controller/GroupRelationController';
import GroupMemberService from '@/app/com/main/module/business/group/service/GroupMemberService';
import CommonIcon from '@/platform/common/web/CommonIcon';

import groupInfoViewModel from '@/platform/vue/view/model/GroupInfoViewModel';


import Prompt from '@/platform/wap/common/Prompt';
import GroupBox from '@/app/com/main/module/business/group/box/GroupBox';
import GroupRelationBox from '@/app/com/main/module/business/group/box/GroupRelationBox';
import Group from '@/app/com/main/module/business/group/bean/Group';
import GroupRelation from '@/app/com/main/module/business/group/bean/GroupRelation';
import GroupChatViewController from '@/app/com/main/module/business/chat/controller/GroupChatViewController';
import RouterUtil from '@/common/vue/RouterUtil';

@Component({
    components: {
        BackButton,
    },
})
export default class GroupInfoCard extends Vue {
    private infoBg: string = 'assets/general/wap/images/info/bg2.jpg';
    private model = groupInfoViewModel;
    private noLogo = CommonIcon.noLogo;
    private openDialog: boolean = false;
    private remark: string = '';

    @Prop({
        type: String,
        required: false,
        default: () => (''),
    })
    private groupId!: string;

    public mounted() {
        this.setGroupId(this.groupId);
    }

    public setGroupId(groupId: string) {
        // this.groupId = groupId;
        const groupBox: GroupBox = app.appContext.getMaterial(GroupBox);
        const box: GroupRelationBox = app.appContext.getMaterial(GroupRelationBox);
        const group: Group = groupBox.getGroup(groupId);
        let relation: GroupRelation = new GroupRelation();
        const list = box.getGroupInGroupRelationListByGroupId(groupId);
        if (list && list.length > 0) {
            relation = list[0];
        }
        this.setGroup(group, relation);
    }

    public setGroup(group: Group, relation: GroupRelation) {
        this.model.setGroup(group);
        this.model.setRelation(relation);
    }


    private getNickname(user: User): string {
        const service: GroupMemberService = app.appContext.getMaterial(GroupMemberService);
        const groupId = this.groupId;
        let nickname = '';
        if (user) {
            nickname = service.getUserShowName(groupId, user);
        }
        return nickname;
    }

    private openSend() {
        const groupId = this.groupId;
        this.onOpenSend(groupId);
    }

    // @Emit('on-to-send')
    private onOpenSend(key: string) {

        // this.$router.push({path: '/chat.user'});

        const groupChatViewController: GroupChatViewController = app.appContext.getMaterial(GroupChatViewController);
        groupChatViewController.showGroupChatById(key);
    }

    private openRemark(): void {
        const own = this;
        const groupId = this.groupId;
        const oldRemark = (this.model.relation) ? this.model.relation.remark : '';
        this.remark = oldRemark;
        this.openDialog = true;
    }

    private updateRemark(): void {
        const own = this;
        const groupId = this.groupId;
        const oldRemark = (this.model.relation) ? this.model.relation.remark : '';
        let text = this.remark;

        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            own.model.relation.remark = text;
                            own.openDialog = false;
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
        const ccc: GroupRelationController = app.appContext.getMaterial(GroupRelationController);
        if (!text) {
            text = '';
        }
        ccc.updateRemark(groupId, text, back);
    }

    private openMemberList() {
        const groupId = this.groupId;
        const path = '/group.info.member.list/' + groupId;
        RouterUtil.toByPath(path);
    }
}
</script>
<style lang="scss">
.v-parallax__content {
    padding: 0;
}
</style>
<style lang="scss">

</style>
