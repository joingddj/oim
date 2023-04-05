<template>
    <div class="query-pane">
        <div class="top">
            <v-toolbar
                color="primary"
                dark
                dense
            >
                <back-button></back-button>
                <v-text-field
                    hide-details
                    single-line
                    v-model="query.queryText"
                ></v-text-field>

                <v-btn @click="handleSearch" icon>
                    <v-icon>search</v-icon>
                </v-btn>
            </v-toolbar>
        </div>
        <div class="center">
            <v-list>
                <template v-for="(item, i) in list">
                    <v-card>
                        <v-list-item
                            :key="item.id"
                            @click=""
                        >
                            <v-avatar>
                                <v-img :src="item.avatar"></v-img>
                            </v-avatar>
                            <div style="width: 15px;"></div>
                            <v-list-item-content>
                                <v-list-item-title>{{ item.name }}({{ item.number }})</v-list-item-title>
                                <v-list-item-subtitle>{{ item.introduce }}</v-list-item-subtitle>
                            </v-list-item-content>
                            <v-btn @click="handleJoinGroup(item.id)" icon>
                                <v-icon>mdi-plus</v-icon>
                            </v-btn>
                        </v-list-item>
                    </v-card>
                </template>
            </v-list>
        </div>
        <div class="bottom">
            <div class="text-center" style="width: 100%">
                <v-pagination
                    v-model="page.number"
                    :length="page.totalPage"
                    @input="handlePage"
                    circle
                ></v-pagination>
            </div>
        </div>
        <GroupJoin ref="joinGroupView"></GroupJoin>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';

import GroupQuery from '@/app/com/main/module/business/group/data/GroupQuery';
import Page from '@/app/com/common/data/Page';
import app from '@/app/App';
import GroupInfoController from '@/app/com/main/module/business/group/controller/GroupInfoController';
import Group from '@/app/com/main/module/business/group/bean/Group';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import GroupInfoUtil from '@/app/com/main/common/util/GroupInfoUtil';

import GroupJoin from '@/views/module/group/find/GroupJoin.vue';

@Component({
    components: {
        BackButton,
        GroupJoin,
    },
})
export default class GroupFindPane extends Vue {

    private query: GroupQuery = new GroupQuery();
    private page: Page = new Page();
    private list: Group[] = [];

    public mounted() {
        // do something
        // this.page.size = 3;
    }

    private handlePage(value: number): void {
        const own = this;
        own.page.number = value;
        this.queryList();
    }

    private handlePageSize(value: number): void {
        const own = this;
        own.page.size = value;
        this.queryList();
    }

    private handleSearch(): void {
        const own = this;
        own.page.number = 1;
        this.queryList();
    }

    private queryList(): void {

        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success && data.body) {
                            const list: Group[] = data.body.items;
                            const p: Page = data.body.page;
                            own.setList(list, p);
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

        const query: GroupQuery = this.query;
        const page: Page = this.page;
        const uc: GroupInfoController = app.appContext.getMaterial(GroupInfoController);
        uc.queryGroupList(query, page, back);
    }

    private setList(list: Group[], page: Page) {
        if (!list) {
            list = [];
        }
        if (page) {
            const totalCount = page.totalCount;
            this.page.totalCount = totalCount;
            this.page.totalPage = page.totalPage;
        }
        for (const group of list) {
            GroupInfoUtil.handleAvatar(group);
        }
        this.list = list;
    }

    private handleJoinGroup(groupId: string) {
        const joinGroupViewName = 'joinGroupView';
        const joinGroupView: any = this.$refs[joinGroupViewName];
        joinGroupView.setGroupId(groupId);
        joinGroupView.setShow(true);
    }
}
</script>

<style lang="scss">
.query-pane {
    width: 100%;
    height: 100%;
    overflow: hidden;

    .center {
        height: calc(100% - 135px);
        bottom: 48px;
        overflow-y: auto;
    }
}

</style>
