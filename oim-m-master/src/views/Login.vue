<template>
    <div class="login-pane">
        <!--        <v-parallax style="height: 100%" src="../images/login/bg.jpg">-->
        <div class="login-logo">
            <img :src="model.data.avatar"/>
        </div>
        <div v-show="!model.hasLogin">
            <v-form
                ref="form"
                lazy-validation>
                <v-text-field
                    v-model="model.data.account"
                    :counter="20"
                    :rules="rules.accountRule"
                    :label="''"
                    :placeholder="'账号'"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="model.data.password"
                    type="password"
                    :counter="20"
                    :label="''"
                    :rules="rules.passwordRule"
                    :placeholder="'密码'"
                    required
                ></v-text-field>
            </v-form>
            <div class="login-button">
                <v-btn color="primary" @click.native="login">{{ '登录' }}</v-btn>
            </div>
            <div class="login-bottom">
                <div style="float: left;">
                    <a @click="register" class="login-register" href="javascript:">立即注册</a>
                </div>
                <div style="float: right;">
                    <!--                    <a @click="resetPassword" class="login-forget" href="javascript:">忘记密码？</a>|-->
                    <a @click="setting" class="login-forget" href="javascript:">设置</a>
                </div>
            </div>
        </div>
        <Loading :show="model.hasLogin"></Loading>

        <!--        </v-parallax>-->
        <v-dialog
            v-model="settingDialog"
            max-width="500px"
        >
            <v-card>
                <v-card-title>
                    设置服务器地址
                </v-card-title>
                <v-card-text>
                    <v-text-field
                        v-model="serverUrl"
                        :placeholder="'服务器地址'"
                        required
                    ></v-text-field>
                </v-card-text>
                <v-card-actions>
                    <v-btn
                        text
                        @click="settingDialog = false"
                    >
                        取消
                    </v-btn>
                    <v-btn
                        color="primary"
                        text
                        @click="saveSetting"
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

import Loading from '@/views/component/loading/Loading.vue';
import app from '@/app/App';
import loginViewModel from '@/platform/vue/view/model/LoginViewModel';

import AppSettingManager from '@/app/com/client/module/setting/manager/AppSettingManager';
import ServerService from '@/app/com/main/module/business/server/service/ServerService';

import Prompt from '@/platform/wap/common/Prompt';

@Component({
    components: {
        Loading,
    },
})
export default class Login extends Vue {
    private model = loginViewModel;
    private rules = {
        accountRule: [
            (v: any) => !!v || '用户名不能为空',
            (v: string) => (/^[\S]{4,20}$/.test(v) || '4-20位'),
        ],
        passwordRule: [
            (v: any) => !!v || '密码不能为空',
            (v: string) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
        ],
    };
    public onLogin: (success: boolean, message?: string) => void = (
        (success: boolean, message?: string) => {
            if (message) {
                Prompt.error(message);
            }
            if (success) {
                this.toMain();
            }
        }
    );

    private settingDialog: boolean = false;
    private serverUrl: string = '';
    private formName = 'form';

    public mounted() {
        // no
        loginViewModel.onLogin = this.onLogin;
        loginViewModel.initialize();
        loginViewModel.data.savePassword = true;
        loginViewModel.data.autoLogin = true;
    }


    public login(): void {
        const own = this;
        const form: any = this.$refs[this.formName];
        const valid = form.validate();
        if (valid) {
            const back = this.onLogin;
            loginViewModel.login(
                () => {
                    return true;
                },
                back);
        }
    }

    public resetPassword(): void {
        Prompt.error('还没实现😁');
    }

    public setting(): void {
        const asm: AppSettingManager = app.appContext.getMaterial(AppSettingManager);
        this.serverUrl = asm.getServerUrl();
        this.settingDialog = true;
    }

    public saveSetting(): void {
        const own = this;
        const url = this.serverUrl;
        if ('' !== url && null !== url) {
            const asm: AppSettingManager = app.appContext.getMaterial(AppSettingManager);
            asm.setServerUrl(url);

            const addressBack = (success: boolean, message?: string) => {
                if (!success) {
                    Prompt.error('获取服务器地址失败！请检查网络是否正常');
                }
                own.settingDialog = false;
            };

            const serverService: ServerService = app.appContext.getMaterial(ServerService);
            serverService.loadServerAddress(addressBack);
        }
    }

    public register(): void {
        this.$router.push({path: '/register'});
    }

    private toMain(): void {
        // history.replaceState()
        // location.replace('/#/main')
        this.$router.replace('/main');
        // this.$router.push({path: '/main'});
    }

    get getHeight(): number {
        const height = document.documentElement.clientHeight;
        return height;
    }
}
</script>

<style lang="scss">
.login-pane {
    width: 100%;
    /*height: 100%;*/
    overflow-y: auto;

    .login-logo {
        margin: 50px auto;
        text-align: center;

        img {
            width: 110px;
            height: 110px;
            border-radius: 50%;
        }
    }

    form {
        margin: auto;
        width: 80%;
    }

    .login-button {
        width: 80%;
        padding-top: 20px;
        margin: auto;

        button {
            width: 100%;
            margin: 0;
        }

        > h3 {
            margin: 20px 0 0 0;
            text-align: center;

            a {
                text-decoration: none;
            }
        }
    }

    .login-bottom {
        width: 80%;
        padding-top: 10px;
        /*margin: auto;*/
        margin: 20px auto;

        > h3 {
            margin: 20px 0 0 0;
            text-align: center;

            a {
                text-decoration: none;
            }
        }
    }

    /*.login-register {*/
    /*    width: 50%;*/
    /*    font-size: 14px;*/
    /*    line-height: 1.5;*/
    /*    color: #333333;*/
    /*    text-transform: uppercase;*/
    /*}*/

    /*.login-forget {*/
    /*    width: 50%;*/
    /*    font-size: 15px;*/
    /*    line-height: 1.5;*/
    /*    color: #fafafa;*/
    /*    text-transform: uppercase;*/
    /*}*/
}

.login-pane::-webkit-scrollbar {
    display: none;
}
</style>
