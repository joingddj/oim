<template>
    <div class="common-main-scroll-pane register-pane">
        <div class="register-logo">
            <img :src="logo"/>
        </div>
        <v-form
                ref="form"
                v-model="valid"
                lazy-validation>
            <v-text-field
                    v-model="user.account"
                    :counter="20"
                    :rules="rules.account"
                    :label="''"
                    :placeholder="'账号'"
                    :error-messages="errors"
                    required
            ></v-text-field>

            <v-text-field
                    v-model="user.password"
                    type="password"
                    :counter="20"
                    :rules="rules.password"
                    :label="''"
                    :placeholder="'密码'"
                    required
            ></v-text-field>

            <v-text-field
                    v-model="user.repeatPassword"
                    type="password"
                    :counter="20"
                    :rules="rules.confirmPassword"
                    :label="''"
                    :placeholder="'确认密码'"
                    required
            ></v-text-field>
        </v-form>

        <div class="register-button">
            <v-btn :disabled="!valid" color="primary" @click.native="register">{{'注册'}}</v-btn>
            <h3>{{'已有账号？'}}
                <label @click="toLogin">{{'去登录'}}</label>
            </h3>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

    import app from '@/app/App';
    import RegisterData from '@/app/com/main/module/business/account/data/RegisterData';
    import SecurityQuestion from '@/app/com/main/module/business/user/bean/SecurityQuestion';
    import QuestionData from '@/app/com/main/module/business/user/data/QuestionData';
    import AccountCall from '@/app/com/main/module/business/account/call/AccountCall';
    import StringUtil from '@/app/common/util/StringUtil';
    import BaseUtil from '@/app/lib/util/BaseUtil';
    import AccountController from '@/app/com/main/module/business/account/controller/AccountController';


    import appInfo from '@/platform/common/config/AppInfo';
    import RouterUtil from '@/common/vue/RouterUtil';
    import Prompt from '@/platform/wap/common/Prompt';


    @Component({
        components: {},
    })
    export default class Register extends Vue {
        private logo: string = appInfo.logoInfo.logo128;
        private user: RegisterData = new RegisterData();
        private questionData: QuestionData = new QuestionData();

        private formName = 'form';
        private exist = false;
        private errors: string[] = [];
        private valid: boolean = false;
        private rules = {
            account: [
                (v: any) => !!v || '用户名不能为空',
                (v: any) => (/^[\S]{4,20}$/.test(v) || '4-20位'),
                (v: any) => (!/^[0-9]*$/.test(v) || '不能纯数字'),
                (v: any) => (/^[\u4E00-\u9FA5-_A-Za-z0-9]+$/.test(v) || '4-16位字母数字组合或者字母、下划线'),
                (v: any) => (!this.exist || '账号已存在'),
            ],
            password: [
                (v: any) => !!v || '密码不能为空',
                // /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(v)
                (v: any) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
            ],
            confirmPassword: [
                (v: any) => !!v || '密码不能为空',
                (v: any) => (/^[\S]{6,20}$/.test(v) || '6-20位'),
                (v: any) => (this.user.password === v || '两次密码不一致'),
            ],
        };

        public register(): void {
            const own = this;
            const form: any = this.$refs[this.formName];
            const valid = form.validate();
            if (valid) {
                const back: (success: boolean) => void = (success: boolean) => {
                    if (success) {
                        this.reset();
                        Prompt.error('注册成功');
                    }
                };
                const controller: AccountController = app.appContext.getMaterial(AccountController);
                const user = this.user;
                const questions = this.questionData.questions;
                controller.register(user, questions, back);
            }
        }

        public reset() {
            const form: any = this.$refs[this.formName];
            form.reset();
        }

        @Watch('user.account')
        public account(newVal: string, oldVal: string) {
            const own = this;
            const client: AccountCall = app.appContext.getMaterial(AccountCall);
            client.isExistAccount(this.user.account, (exist: boolean) => {
                own.exist = exist;
                own.errors = exist ? ['账号已经存在'] : [];
            });
        }

        private toLogin(): void {
            RouterUtil.back();
        }
    }
</script>

<style lang="scss">
    .register-pane {
        width: 100%;

        .register-logo {
            margin: 50px auto;
            text-align: center;

            img {
                width: 110px;
                border-radius: 50%;
            }
        }

        form {
            margin: auto;
            width: 80%;
        }

        .register-button {
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

        .register-bottom {
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
    }
</style>
