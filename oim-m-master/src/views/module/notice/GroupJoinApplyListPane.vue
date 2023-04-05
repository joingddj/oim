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
                            <v-img :src="item.user.avatar"></v-img>
                        </v-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline">
                                {{ item.user.nickname }}({{ item.user.account }})
                            </v-list-item-title>
                            <v-list-item-subtitle>{{ item.user.signature }}</v-list-item-subtitle>
                        </v-list-item-content>
                        <div v-if="item.apply.handleType==='0'">
                            <v-btn icon @click="accept(item.apply)">
                                <v-icon color="#05CA65">fas fa-check-circle</v-icon>
                            </v-btn>
                            <v-btn icon @click="reject(item.apply)">
                                <v-icon color="#F54949">fas fa-times-circle</v-icon>
                            </v-btn>
                        </div>
                    </v-list-item>
                    <v-card-text>
                        申请加入
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
                    <div class="more">
                        <div>
                            {{ item.apply.message }}
                        </div>
                        <div v-for="(data, index) in item.answers" :key="index">
                            <div class="item">
                                <div>
                                    <div>
                                        <span>问题:</span>
                                        <label>
                                            {{ data.question }}
                                        </label>
                                    </div>
                                </div>
                                <div>
                                    <div>
                                        <span>答案:</span>
                                        <label>{{ data.answer }}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
import GroupBox from '@/app/com/main/module/business/group/box/GroupBox';

@Component({
    components: {
        BackButton,
    },
})
export default class GroupJoinApplyListPane extends Vue {
    private list: GroupJoinApplyEntityCase[] = [];
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
        const query: GroupJoinApplyQuery = new GroupJoinApplyQuery();
        query.handleType = GroupJoinApply.HANDLE_TYPE_UNTREATED;
        const groupJoinController: GroupJoinController = app.appContext.getMaterial(GroupJoinController);
        groupJoinController.queryApplyDataReceiveList(query, page, (p, items) => {
            own.setList(items, p);
        });
    }

    private setList(list: GroupJoinApplyEntityCase[], page: Page) {
        if (!list) {
            list = [];
        }
        if (page) {
            const totalCount = page.totalCount;
            this.page.totalCount = totalCount;
        }
        const groupBox: GroupBox = app.appContext.getMaterial(GroupBox);

        for (const data of list) {
            if (!data.user) {
                data.user = new User();
            }
            if (!data.user) {
                data.user = new User();
            }
            if (!data.apply) {
                data.apply = new GroupJoinApply();
            }
            UserInfoUtil.handleAvatar(data.user);

            const groupId = data.apply.groupId;
            data.group = groupBox.getGroup(groupId);
        }
        this.list = list;
    }

    private reject(apply: GroupJoinApply): void {
        this.joinHandle(GroupJoinApply.HANDLE_TYPE_REJECT, apply);
    }

    private accept(apply: GroupJoinApply): void {
        this.joinHandle(GroupJoinApply.HANDLE_TYPE_ACCEPT, apply);
    }

    private joinHandle(handleType: string, apply: GroupJoinApply) {
        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            apply.handleType = handleType;
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

        const handle: GroupJoinHandleData = new GroupJoinHandleData();
        const contactController: GroupJoinController = app.appContext.getMaterial(GroupJoinController);
        handle.handleType = handleType;
        handle.applyIds.push(apply.id);
        contactController.joinHandle(handle, back);
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
