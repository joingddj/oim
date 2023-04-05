<template>
    <div>
        <v-toolbar
            color="primary"
            dark
        >
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
            <v-toolbar-title>联系人</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-menu offset-y>
                <template v-slot:activator="{ on }">
                    <v-btn
                        v-on="on"
                        icon>
                        <v-icon>mdi-plus</v-icon>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-item
                        @click="findUser"
                    >
                        <v-list-item-title>添加联系人</v-list-item-title>
                    </v-list-item>
                    <v-list-item
                        @click="findGroup"
                    >
                        <v-list-item-title>查找群</v-list-item-title>
                    </v-list-item>
                    <v-list-item
                        @click=""
                    >
                        <v-list-item-title>发起群聊</v-list-item-title>
                    </v-list-item>
                </v-list>
            </v-menu>

        </v-toolbar>
        <v-card>
            <v-list dense>
                <v-list-item-group color="primary">
                    <v-list-item
                        @click="noticeContactAddApplyList"
                    >
                        <v-list-item-icon>
                            <v-icon>mdi-account</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ '新联系人' }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item
                        @click="noticeGroupHandle"
                    >
                        <v-list-item-icon>
                            <v-icon>group</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>{{ '群通知' }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-card>
        <v-tabs
            v-model="contactNav.active"
            height="40"
        >
            <v-tab :key="'0'"
            >
                {{ '联系人' }}
            </v-tab>
            <v-tab :key="'1'"
            >
                {{ '分组' }}
            </v-tab>
            <v-tab :key="'2'"
            >
                {{ '群' }}
            </v-tab>
        </v-tabs>
        <v-tabs-items v-model="contactNav.active">
            <v-tab-item :key="'0'"
            >
                <contact-all-list-pane></contact-all-list-pane>
            </v-tab-item>
            <v-tab-item :key="'1'"
            >
                <user-list-pane></user-list-pane>
            </v-tab-item>
            <v-tab-item :key="'2'"
            >
                <group-list-pane></group-list-pane>
            </v-tab-item>
        </v-tabs-items>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import contactNavActive from '@/views/main/list/ContactNavActive';

import ContactAllListPane from '@/views/main/list/ContactAllListPane.vue';
import UserListPane from '@/views/main/list/ContactListPane.vue';
import GroupListPane from '@/views/main/list/GroupListPane.vue';

@Component({
    components: {
        ContactAllListPane,
        UserListPane,
        GroupListPane,
    },
})
export default class ContactListNavPane extends Vue {
    private contactNav = contactNavActive;

    public mounted() {
        // no
    }

    public findUser() {
        this.$router.push({path: '/user.find'});
    }

    public findGroup() {
        this.$router.push({path: '/group.find'});
    }

    private noticeContactAddApplyList() {
        const path = '/notice.contact.add.apply.list';
        this.$router.push({path: path});
    }

    private noticeGroupHandle() {
        const path = '/notice.group.handle';
        this.$router.push({path: path});
    }
}
</script>

<style lang="scss" scoped>

</style>
