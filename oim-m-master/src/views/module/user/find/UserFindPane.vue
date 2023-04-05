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
                                <v-list-item-title>{{ item.nickname }}({{ item.account }})</v-list-item-title>
                                <v-list-item-subtitle>{{ item.signature }}</v-list-item-subtitle>
                            </v-list-item-content>
                            <v-btn @click="handleAddUser(item.id)" icon>
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
        <ContactAddApply ref="addUserView"></ContactAddApply>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';


import UserQuery from '@/app/com/main/module/business/user/data/UserQuery';
import Page from '@/app/com/common/data/Page';
import app from '@/app/App';
import UserInfoController from '@/app/com/main/module/business/user/controller/UserInfoController';
import User from '@/app/com/main/module/business/user/bean/User';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import UserInfoUtil from '@/app/com/main/common/util/UserInfoUtil';

import ContactAddApply from '@/views/module/contact/apply/ContactAddApply.vue';

@Component({
    components: {
        BackButton,
        ContactAddApply,
    },
})
export default class UserQueryPane extends Vue {
    private query: UserQuery = new UserQuery();
    private page: Page = new Page();
    private list: User[] = [];

    private items: User[] = [];

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
                            const list: User[] = data.body.items;
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

        const query: UserQuery = this.query;
        const page: Page = this.page;
        const uc: UserInfoController = app.appContext.getMaterial(UserInfoController);
        uc.queryUserList(query, page, back);
    }

    private setList(list: User[], page: Page) {
        if (!list) {
            list = [];
        }
        if (page) {
            const totalCount = page.totalCount;
            this.page.totalCount = totalCount;
            this.page.totalPage = page.totalPage;
        }
        for (const user of list) {
            UserInfoUtil.handleAvatar(user);
        }
        this.list = list;
    }


    private handleAddUser(userId: string) {
        const addUserViewName = 'addUserView';
        const addUserView: any = this.$refs[addUserViewName];
        addUserView.setUserId(userId);
        addUserView.setShow(true);
    }

    public mounted() {
        // this.page.setSize(5);
        // if (this.items.length > 0) {
        //     return;
        // }
        // const names: string[] = ['网', 'xk', '和', '在', '他', '0', '我', '啊', '么', '了', '来',];
        // const l = names.length;
        // let j = 1;
        // for (let i = 0; i < 100; i++) {
        //     const index = Math.round(Math.random() * l);
        //     if (j > 29) {
        //         j = 1;
        //     }
        //     const data: User = new User();
        //     data.id = i + '';
        //     data.signature = '这是地' + i + '个';
        //     data.nickname = names[index] + '牛B' + i + '号';
        //     data.avatar = 'assets/images/common/head/user/' + j + '.png';
        //     j++;
        //     this.items.push(data);
        // }
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

/*.query-list-pane {*/
/*    width: 100%;*/
/*    height: 100%;*/
/*    overflow-y: auto;*/
/*    padding-bottom: 56px;*/
/*}*/
</style>
