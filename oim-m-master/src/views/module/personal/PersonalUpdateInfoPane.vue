<template>
    <div class="common-main-scroll-pane">
        <v-app-bar
                color="primary"
                dark
        >
            <back-button></back-button>

            <v-toolbar-title>{{ '修改个人信息' }}</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-card
                elevation="3"
        >
            <v-col>
                <v-form
                        ref="form"
                        v-model="valid"
                        lazy-validation
                >
                    <v-text-field
                            required
                            :counter="100"
                            label="昵称"
                            v-model="user.nickname"
                            :rules="rules.nickname"
                    ></v-text-field>
                    <v-text-field
                            :counter="200"
                            label="个性签名"
                            v-model="user.signature"
                    ></v-text-field>
                    <v-text-field
                            required
                            :counter="11"
                            label="手机"
                            v-model="user.mobile"
                            :rules="rules.mobile"
                    ></v-text-field>
                    <v-text-field
                            required
                            label="邮箱"
                            v-model="user.email"
                            :rules="rules.email"
                    ></v-text-field>
                    <v-text-field
                            required
                            :counter="100"
                            label="姓名"
                            v-model="user.name"
                            :rules="rules.name"
                    ></v-text-field>
                    <v-radio-group
                            label="性别"
                            v-model="user.gender">
                        <v-radio
                                :label="'男'"
                                :value="'1'"
                        ></v-radio>
                        <v-radio
                                :label="'女'"
                                :value="'2'"
                        ></v-radio>
                        <v-radio
                                :label="'保密'"
                                :value="'3'"
                        ></v-radio>
                    </v-radio-group>
                </v-form>
            </v-col>
        </v-card>
        <v-card>
            <v-col>
                <v-btn
                        :disabled="!valid"
                        block
                        color="primary"
                        @click="handleUpdate"
                >
                    保存
                </v-btn>
            </v-col>
        </v-card>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';
import app from '@/app/App';
import Prompt from '@/platform/wap/common/Prompt';
import DataBackAction from '@/app/base/net/DataBackAction';
import User from '@/app/com/main/module/business/user/bean/User';
import PersonalBox from '@/app/com/main/module/business/personal/box/PersonalBox';
import PersonalController from '@/app/com/main/module/business/personal/controller/PersonalController';

@Component({
    components: {
        BackButton,
    },
})
export default class PersonalUpdatePane extends Vue {
    private valid: boolean = false;
    private user: User = new User();
    private name: string = '';
    private rules = {
        nickname: [
            (v: any) => !!v || '不能为空',
        ],
        name: [
            (v: any) => !!v || '不能为空',
        ],
        email: [
            // (v: any) => !!v || '不能为空',
            (v: any) => /.+@.+\..+/.test(v) || '格式不正确',
        ],
        mobile: [
            // (v: any) => !!v || '不能为空',
            (v: any) => /^1[3456789]\d{9}$/.test(v) || '格式不正确',
        ],
    };

    public mounted() {
        const pb: PersonalBox = app.appContext.getMaterial(PersonalBox);
        const user: User = pb.getUser();
        this.setUser(user);
    }

    private setUser(user: User) {
        this.user = user;
    }

    private handleUpdate(): void {
        const own = this;
        const pc: PersonalController = app.appContext.getMaterial(PersonalController);
        const back: DataBackAction = {
            back(data: any): void {
                if (data) {
                    const info = data.info;
                    if (info) {
                        if (info.success) {
                            Prompt.notice('成功');
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

        const user: User = new User();
        Object.assign(user, this.user);

        const form: any = this.$refs['form'];
        const valid = form.validate();
        if (valid) {
            pc.updateUser(user, back);
        }
    }
}
</script>

<style lang="scss" scoped>

</style>
