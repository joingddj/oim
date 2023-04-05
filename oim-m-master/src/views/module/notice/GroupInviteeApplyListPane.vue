<template>
    <v-card
            class="mx-auto"
            light
    >
        <v-card-title>
            <v-pagination
                    v-model="page.number"
                    :length="page.totalPage"
                    @input="handlePage"
                    circle
            >
            </v-pagination>
            <v-icon
                    large
                    right
                    @click="handleLoadList"
            >
                mdi-refresh
            </v-icon>
        </v-card-title>
        <v-list>
            <template v-for="(item, i) in list">
                <v-card
                        class="mx-auto"
                >
                    <v-list-item>
                        <v-avatar>
                            <v-img :src="item.inviterUser.avatar"></v-img>
                        </v-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline">
                                {{ item.inviterUser.nickname }}({{ item.inviterUser.account }})
                            </v-list-item-title>
                            <v-list-item-subtitle>{{ item.inviterUser.signature }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-card-text>
                        邀请您加入
                    </v-card-text>
                    <v-list-item>
                        <v-avatar>
                            <v-img :src="item.group.avatar"></v-img>
                        </v-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline">{{ item.group.name }}({{ item.group.number }})
                            </v-list-item-title>
                            <v-list-item-subtitle>{{ item.group.introduce }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <div v-if="item.apply.inviteeHandleType==='0'">
                            <v-btn icon @click="accept(item.apply)">
                                <v-icon color="#05CA65">fas fa-check-circle</v-icon>
                            </v-btn>
                            <v-btn icon @click="reject(item.apply)">
                                <v-icon color="#F54949">fas fa-times-circle</v-icon>
                            </v-btn>
                        </div>
                    </v-card-actions>
                </v-card>
            </template>
        </v-list>
    </v-card>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';

import app from '@/app/App';
import GroupJoinController from '@/app/com/main/module/business/group/controller/GroupJoinController';
import GroupJoinApply from '@/app/com/main/module/business/group/bean/GroupJoinApply';
import Page from '@/app/com/common/data/Page';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import UserInfoUtil from '@/app/com/main/common/util/UserInfoUtil';
import User from '@/app/com/main/module/business/user/bean/User';
import GroupJoinHandleData from '@/app/com/main/module/business/group/data/GroupJoinHandleData';
import GroupJoinApplyEntityCase from '@/app/com/main/module/business/group/data/GroupJoinApplyEntityCase';
import GroupJoinApplyQuery from '@/app/com/main/module/business/group/data/GroupJoinApplyQuery';
import GroupInviteController from '@/app/com/main/module/business/group/controller/GroupInviteController';
import GroupInviteApplyQuery from '@/app/com/main/module/business/group/data/GroupInviteApplyQuery';
import GroupInviteApply from '@/app/com/main/module/business/group/bean/GroupInviteApply';
import GroupInviteVerifyHandleData from '@/app/com/main/module/business/group/data/GroupInviteVerifyHandleData';
import GroupBox from '@/app/com/main/module/business/group/box/GroupBox';
import Group from '@/app/com/main/module/business/group/bean/Group';
import GroupInfoUtil from '@/app/com/main/common/util/GroupInfoUtil';
import GroupInviteeApplyQuery from '@/app/com/main/module/business/group/data/GroupInviteeApplyQuery';
import GroupInviteeHandleData from '@/app/com/main/module/business/group/data/GroupInviteeHandleData';
import GroupInviteeController from '@/app/com/main/module/business/group/controller/GroupInviteeController';

@Component({
    components: {
        BackButton,
    },
})
export default class GroupInviteeApplyListPane extends Vue {
    private list: any[] = [];
    private page: Page = new Page();

    public mounted() {
        this.loadList();
    }

    @Watch('$route')
    private onChangeRoute(): void {
        this.loadList();
    }

    private handlePage(value: number): void {
        const own = this;
        own.page.number = value;
        this.loadList();
    }

    private handlePageSize(value: number): void {
        const own = this;
        own.page.size = value;
        this.loadList();
    }

    private handleLoadList(): void {
        const own = this;
        own.page.number = 1;
        this.loadList();
    }

    private loadList(): void {
        const own = this;
        const page: Page = this.page;
        const query: GroupInviteeApplyQuery = new GroupInviteeApplyQuery();
        query.inviteeHandleType = GroupInviteApply.INVITEE_HANDLE_TYPE_UNTREATED;
        const controller: GroupInviteeController = app.appContext.getMaterial(GroupInviteeController);
        controller.queryInviteeDataList(query, page, (p, items) => {
            own.setList(items, p);
        });
    }

    private setList(list: any[], page: Page) {
        if (!list) {
            list = [];
        }
        if (page) {
            const totalCount = page.totalCount;
            this.page.totalCount = totalCount;
        }
        const groupBox: GroupBox = app.appContext.getMaterial(GroupBox);
        for (const data of list) {
            if (!data.inviterUser) {
                data.inviterUser = new User();
            }
            if (!data.apply) {
                data.apply = new GroupInviteApply();
            }
            if (!data.group) {
                data.group = new Group();
            }
            UserInfoUtil.handleAvatar(data.inviterUser);
            GroupInfoUtil.handleAvatar(data.group);
        }
        this.list = list;
    }

    private reject(apply: GroupInviteApply): void {
        this.inviteHandle(GroupInviteApply.VERIFY_HANDLE_TYPE_REJECT, apply);
    }

    private accept(apply: GroupInviteApply): void {
        this.inviteHandle(GroupInviteApply.VERIFY_HANDLE_TYPE_ACCEPT, apply);
    }

    private inviteHandle(handleType: string, apply: GroupInviteApply) {
        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            apply.inviteeHandleType = handleType;
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

        const handle: GroupInviteeHandleData = new GroupInviteeHandleData();
        const controller: GroupInviteeController = app.appContext.getMaterial(GroupInviteeController);
        handle.inviteeHandleType = handleType;
        handle.applyIds.push(apply.id);
        controller.inviteeHandle(handle, back);
    }
}
</script>

<style lang="scss" scoped>
.action .attr {
    cursor: pointer;
    margin-top: 10px;
    color: #1c4eec;
    height: 19px;
    line-height: 1.5;
    font-size: 14px;
}

.more {
    .item {
        padding: 10px;
    }
}
</style>
