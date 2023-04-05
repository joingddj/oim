<template>
    <div class="oim-main">
        <div class="main-pane-content">
            <v-main>
                <!-- Provides the application the proper gutter -->
                <message-list-pane v-show="data.tab===tabs.messageTab.key"></message-list-pane>
                <contact-list-nav-pane v-show="data.tab===tabs.contactTab.key"></contact-list-nav-pane>
                <profile-pane v-show="data.tab===tabs.personalTab.key"></profile-pane>
            </v-main>
        </div>

        <v-bottom-navigation
            v-model="data.tab"
            grow
            app
            color="primary"
        >
            <template v-for="tab in data.tabs">
                <v-btn :value="tab.key">
                    <span>{{ tab.title }}</span>
                    <v-badge
                        :content="tab.redCount"
                        :value="tab.redCount"
                        color="red"
                        overlap
                    >
                        <v-icon large>{{ tab.icon }}</v-icon>
                    </v-badge>
                </v-btn>
            </template>
        </v-bottom-navigation>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import mainViewData from '@/platform/wap/view/data/MainViewData';
import mainBaseTabs from '@/platform/wap/view/data/MainBaseTabs';
import MessageListPane from '@/views/module/message/MessageListPane.vue';
import ContactListNavPane from '@/views/main/list/ContactListNavPane.vue';
import ProfilePane from '@/views/main/me/ProfilePane.vue';

import app from '@/app/App';
import MessageAllUnreadView from '@/app/com/main/view/MessageAllUnreadView';
import mainActiveTab from '@/views/main/MainActive';
import Client from '@/app/base/message/client/Client';
import MainView from '@/app/com/client/common/view/MainView';
import WorkViewEnum from '@/app/com/common/view/WorkViewEnum';
import AppUtil from '@/platform/wap/util/AppUtil';


@Component({
    components: {
        MessageListPane,
        ContactListNavPane,
        ProfilePane,
    },
})
export default class Main extends Vue implements MainView {
    private data = mainViewData;
    private tabs = mainBaseTabs;

    // 声明周期钩子
    public mounted() {
        app.appContext.putViewObject(WorkViewEnum.MainView, this);
        mainViewData.initialize();
    }

    public showOtherOnline(offline: boolean, client: Client): void {
        if (offline) {
            AppUtil.logout();
            app.prompt('您的账号在其他地方登录！');
        }
    }
}
</script>

<style lang="scss">
.oim-main {
    width: 100%;
    height: 100%;
    overflow: hidden;
}

.main-pane-bottom {
    position: absolute;
    height: 48px;
    max-height: 48px;
    bottom: 0;
    right: 0;
    left: 0;
    overflow: hidden;
}

.main-pane-content {
    position: absolute;
    top: 0;
    bottom: 50px;
    right: 0;
    left: 0;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
}

.main-pane-content::-webkit-scrollbar {
    display: none;
}
</style>
