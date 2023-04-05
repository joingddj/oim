<template>
    <div class="oim-app">
        <div v-if="appInfo.disconnection" class="popup slide-down" tabindex="-1"
             style="width: 100%;text-align: center;bottom: 1px;position: fixed;top: unset;">
            <span style="color: #ffad33">网络已断开</span>
            <!--            <button v-show="!isReconnect" @click="reconnect" class="oim_button"-->
            <!--                    style="height: 25px;width: 70px;font-size: 12px">重连-->
            <!--            </button>-->
        </div>
        <v-app>
            <router-view/>
        </v-app>
    </div>
</template>

<script lang="ts">
import './styles/lib/materialdesign-webfont/css/materialdesignicons.css';
import './styles/lib/fontawesome-free-5.14.0-web/css/all.css';
import './styles/oim/layout.css';
import './styles/oim/main.css';
import './styles/oim/list.scss';
import './styles/oim/component.scss';
import './styles/oim/chat.scss';

import Vue from 'vue';
import Component from 'vue-class-component';

import app from '@/app/App';
import LoginController from '@/app/com/main/module/business/index/controller/LoginController';
import Prompt from '@/platform/wap/common/Prompt';

@Component({
    components: {},
})
export default class App extends Vue {
    private appInfo = app;
    private isReconnect = false;

    private reconnect() {
        this.isReconnect = true;
        const back = (success: boolean, message?: string) => {
            if (!success) {
                if (message) {
                    Prompt.notice(message);
                }
            }
            this.isReconnect = false;
        };
        const lc: LoginController = app.appContext.getMaterial(LoginController);
        lc.reconnect(back);
    }
}
</script>
<style lang="scss" scoped>

.oim-app {
    width: 100%;
    height: 100%;
    overflow: hidden;

    .v-application {
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
}

/*.oim-app::-webkit-scrollbar {*/
/*    display: none;*/
/*}*/
</style>
