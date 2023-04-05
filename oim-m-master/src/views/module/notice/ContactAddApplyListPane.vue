<template>
    <div class="query-pane">
        <div class="top">
            <v-toolbar
                color="primary"
                dark
                dense
            >
                <back-button></back-button>
                <v-spacer></v-spacer>
                <v-btn @click="handleLoadList" icon>
                    <v-icon>mdi-refresh</v-icon>
                </v-btn>
            </v-toolbar>
        </div>
        <div class="center">
            <v-list>
                <template v-for="(item, i) in list">
                    <v-card>
                        <v-list-item
                        >
                            <v-avatar>
                                <v-img :src="item.user.avatar"></v-img>
                            </v-avatar>
                            <div style="width: 15px;"></div>
                            <v-list-item-content>
                                <v-list-item-title>{{ item.user.nickname }}({{ item.user.account }})</v-list-item-title>
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
                        <div class="more">
                            <div>
                                {{ item.apply.message }}
                            </div>
                            <div v-for="(item, index) in item.answers" :key="index">
                                <div class="item">
                                    <div>
                                        <div>
                                            <span>问题:</span>
                                            <label>{{ item.question }}</label>
                                        </div>
                                    </div>
                                    <div>
                                        <div>
                                            <span>答案:</span>
                                            <label>{{ item.answer }}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
        <ContactAddApplyAccept ref="acceptContactAddApply"></ContactAddApplyAccept>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';

import app from '@/app/App';
import ContactController from '@/app/com/main/module/business/contact/controller/ContactController';
import ContactAddApply from '@/app/com/main/module/business/contact/bean/ContactAddApply';
import Page from '@/app/com/common/data/Page';
import DataBackAction from '@/app/base/net/DataBackAction';
import Prompt from '@/platform/wap/common/Prompt';
import ContactAddApplyEntityCase from '@/app/com/main/module/business/contact/data/ContactAddApplyEntityCase';
import UserInfoUtil from '@/app/com/main/common/util/UserInfoUtil';
import User from '@/app/com/main/module/business/user/bean/User';
import ContactAddHandleData from '@/app/com/main/module/business/contact/data/ContactAddHandleData';
import ContactAddApplyQuery from '@/app/com/main/module/business/contact/data/ContactAddApplyQuery';
import ContactAddApplyController from '@/app/com/main/module/business/contact/controller/ContactAddApplyController';

import ContactAddApplyAccept from '@/views/module/notice/ContactAddApplyAccept.vue';

@Component({
    components: {
        BackButton,
        ContactAddApplyAccept,
    },
})
export default class ContactAddApplyListPane extends Vue {

    private list: ContactAddApplyEntityCase[] = [];
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
        const query: ContactAddApplyQuery = new ContactAddApplyQuery();
        const page: Page = this.page;
        query.handleType = ContactAddApply.HANDLE_TYPE_UNTREATED;

        const controller: ContactAddApplyController = app.appContext.getMaterial(ContactAddApplyController);
        controller.queryApplyDataReceiveList(query, page, (p, items) => {
            own.setList(items, p);
        });

    }

    private setList(list: ContactAddApplyEntityCase[], page: Page) {
        if (!list) {
            list = [];
        }
        if (page) {
            const totalCount = page.totalCount;
            this.page.totalCount = totalCount;
        }
        for (const data of list) {
            if (!data.user) {
                data.user = new User();
            }
            if (!data.user) {
                data.user = new User();
            }
            if (!data.apply) {
                data.apply = new ContactAddApply();
            }
            UserInfoUtil.handleAvatar(data.user);
        }
        this.list = list;
    }

    private reject(apply: ContactAddApply): void {
        const own = this;
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            apply.handleType = '2';
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

        const handle: ContactAddHandleData = new ContactAddHandleData();
        const contactController: ContactController = app.appContext.getMaterial(ContactController);
        handle.handleType = '2';
        handle.applyIds.push(apply.id);
        contactController.sendAddResponse(handle, back);
    }

    private accept(apply: ContactAddApply): void {
        const onBack = (handleType: string): void => {
            apply.handleType = handleType;
        };
        const name = 'acceptContactAddApply';
        const acceptContactAddApplyName = 'acceptContactAddApply';
        const acceptContactAddApply: any = this.$refs[acceptContactAddApplyName];
        acceptContactAddApply.setApplyId(apply.id, onBack);
        acceptContactAddApply.setShow(true);
    }
}
</script>

<style lang="scss" scoped>
.action .attr {
    cursor: pointer;
    margin-top: 10px;
    color: #05ca65;
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
