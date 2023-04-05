<template>
    <div class="common-main-scroll-pane">
        <v-app-bar
                color="primary"
                dark
        >
            <back-button></back-button>

            <v-toolbar-title>{{ '修改密码' }}</v-toolbar-title>
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
                            :counter="20"
                            label="旧密码"
                            v-model="passwordData.oldPassword"
                            :rules="rules.oldPassword"
                    ></v-text-field>
                    <v-text-field
                            :counter="20"
                            label="新密码"
                            v-model="passwordData.tempPassword"
                            :rules="rules.newPassword"
                    ></v-text-field>
                    <v-text-field
                            :counter="20"
                            label="确认密码"
                            v-model="passwordData.repeatPassword"
                            :rules="rules.confirmPassword"
                    ></v-text-field>
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
    private passwordData = {
        oldPassword: '',
        tempPassword: '',
        repeatPassword: '',
    };
    private rules = {
        oldPassword: [
            (v: any) => !!v || '密码不能为空',
            // /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(v)
            (v: any) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
        ],
        newPassword: [
            (v: any) => !!v || '密码不能为空',
            // /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(v)
            (v: any) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
        ],
        confirmPassword: [
            (v: any) => !!v || '密码不能为空',
            (v: any) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
            (v: any) => (this.passwordData.tempPassword === v || '两次密码不一致'),
        ],
    };

    public mounted() {
        // no
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

        const newPassword = this.passwordData.tempPassword;
        const oldPassword = this.passwordData.oldPassword;

        const form: any = this.$refs['form'];
        const valid = form.validate();
        if (valid) {
            pc.updatePassword(oldPassword, newPassword, back);
        }
    }
}
</script>

<style lang="scss">

</style>
