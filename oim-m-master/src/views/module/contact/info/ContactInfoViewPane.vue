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
                        <v-toolbar-title>{{ '用户信息' }}</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-list-item>
                </div>
            </v-img>
        </v-card>

        <div class="user-info-card-head">
            <div>
                <v-avatar size="80">
                    <v-img :src="model.user.avatar"></v-img>
                </v-avatar>
            </div>
            <div class="user-info-card-name-text">
                <div><h2>{{ model.user.nickname }}</h2></div>
                <div><span>{{ model.user.signature }}</span></div>
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
                                <a v-if="model.isContact" @click="openRemark">
                                    <i class="fa fa-edit" aria-hidden="true"></i>
                                </a>
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '数字账号' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.user.number }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '账号' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.user.account }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '姓名' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.user.name }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '邮箱' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ model.user.email }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '性别' }}</v-list-item-title>
                            <v-list-item-subtitle>
                                <span v-if="model.user.gender==='1'">男</span>
                                <span v-if="model.user.gender==='2'">女</span>
                                <span v-if="model.user.gender==='3'">保密</span>
                            </v-list-item-subtitle>
                        </v-list-item-content>
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

import User from '@/app/com/main/module/business/user/bean/User';
import ContactRelation from '@/app/com/main/module/business/contact/bean/ContactRelation';
import app from '@/app/App';
import UserBox from '@/app/com/main/module/business/user/box/UserBox';
import ContactRelationBox from '@/app/com/main/module/business/contact/box/ContactRelationBox';
import ContactRelationController from '@/app/com/main/module/business/contact/controller/ContactRelationController';
import DataBackAction from '@/app/base/net/DataBackAction';
import contactInfoViewModel from '@/platform/vue/view/model/ContactInfoViewModel';
import CommonIcon from '@/platform/common/web/CommonIcon';

import Prompt from '@/platform/wap/common/Prompt';
import UserChatViewController from '@/app/com/main/module/business/chat/controller/UserChatViewController';


@Component({
    components: {
        BackButton,
    },
})
export default class ContactInfoViewPane extends Vue {
    private infoBg: string = 'assets/general/wap/images/info/bg1.jpg';
    private noLogo = CommonIcon.noLogo;
    private model = contactInfoViewModel;

    private show: boolean = false;
    private user: User = new User();
    private relation: ContactRelation = new ContactRelation();
    private remarkEdit: boolean = false;
    private showInfo: boolean = false;

    private openDialog: boolean = false;
    private remark: string = '';

    @Prop({
        type: String,
        required: false,
        default: () => (''),
    })
    private userId!: string;

    public mounted() {
        this.setUserId(this.userId);
    }

    public setUserId(userId: string) {
        const userBox: UserBox = app.appContext.getMaterial(UserBox);
        const contactListBox: ContactRelationBox = app.appContext.getMaterial(ContactRelationBox);
        const user: User = userBox.getUser(userId);
        let relation: ContactRelation = new ContactRelation();
        const list = contactListBox.getContactInContactRelationListByUserId(userId);
        if (list && list.length > 0) {
            relation = list[0];
        }
        this.setUser(user, relation);
    }

    public setUser(user: User, relation: ContactRelation) {
        this.model.setUser(user);
        this.model.setRelation(relation);
    }

    private openSend() {
        const userId = this.userId;
        this.onOpenSend(userId);
    }

    // @Emit('on-to-send')
    private onOpenSend(key: string) {

        // this.$router.push({path: '/chat.user'});
        const controller: UserChatViewController = app.appContext.getMaterial(UserChatViewController);
        controller.showUserChatById(key);
    }

    private openRemark(): void {
        const own = this;
        const oldRemark = (this.relation) ? this.relation.remark : '';
        this.remark = oldRemark;
        this.openDialog = true;
    }

    private updateRemark(): void {
        const own = this;
        const contactUserId = this.userId;
        const oldRemark = (this.relation) ? this.relation.remark : '';
        let text = this.remark;

        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            own.relation.remark = text;
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
        const ccc: ContactRelationController = app.appContext.getMaterial(ContactRelationController);
        if (!text) {
            text = '';
        }
        ccc.updateRemark(contactUserId, text, back);
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
