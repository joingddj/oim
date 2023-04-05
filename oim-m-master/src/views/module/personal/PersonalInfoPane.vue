<template>
    <div class="common-main-scroll-pane">

        <v-card
                class="mx-auto"
                elevation="8"
                width="100%"
                dark
        >

            <v-img
                    height="145"
                    dark
                    :src="infoBg"
            >
                <div class="personal-top">
                    <v-list-item
                            @click=""
                    >
                        <back-button></back-button>
                        <v-toolbar-title>{{ '个人信息' }}</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-list-item>
                </div>
            </v-img>
        </v-card>

        <div class="personal-head">
            <div>
                <v-avatar size="80">
                    <v-img :src="user.avatar"></v-img>
                </v-avatar>
            </div>
            <div class="personal-text">
                <div><h2>{{ user.nickname }}</h2></div>
                <div><span>{{ user.signature }}</span></div>
            </div>
        </div>
        <div class="personal-info">
            <v-card>
                <v-list>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '数字账号' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ user.number }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '账号' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ user.account }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '姓名' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ user.name }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '手机' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ user.mobile }}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item
                            @click=""
                            two-line
                    >
                        <v-list-item-content>
                            <v-list-item-title>{{ '邮箱' }}</v-list-item-title>
                            <v-list-item-subtitle>{{ user.email }}</v-list-item-subtitle>
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
                                <span v-if="user.gender==='1'">男</span>
                                <span v-if="user.gender==='2'">女</span>
                                <span v-if="user.gender==='3'">保密</span>
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
                            @click="update"
                    >
                        编辑信息
                    </v-btn>
                </v-col>
            </v-card>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';

import app from '@/app/App';
import User from '@/app/com/main/module/business/user/bean/User';
import PersonalBox from '@/app/com/main/module/business/personal/box/PersonalBox';


@Component({
    components: {
        BackButton,
    },
})
export default class PersonalInfoPane extends Vue {

    private infoBg: string = 'assets/general/wap/images/info/bg1.jpg';
    private user: User = new User();

    public mounted() {
        const pb: PersonalBox = app.appContext.getMaterial(PersonalBox);
        const user: User = pb.getUser();
        this.setUser(user);
    }

    private setUser(user: User) {
        this.user = user;
    }

    public update() {
        this.$router.push({path: '/personal.update.info'});
    }
}
</script>
<style lang="scss">
.v-parallax__content {
    padding: 0;
}
</style>

<style lang="scss">
.personal-top {
    height: 145px;
    width: 100%;
    text-align: center;
    //background-color: #1abc9c;
    background-color: rgba(120, 120, 255, 0.7);
    overflow: hidden;
}

.personal-head {
    width: 100%;
    margin-top: -50px;
    text-align: center;
    /*position: absolute;*/
    z-index: 10;
    overflow: hidden;
}

.personal-info {
    /*margin-top: 100px;*/
    overflow: hidden;
}

.personal-text {
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    /*width: 110px;*/
}
</style>
