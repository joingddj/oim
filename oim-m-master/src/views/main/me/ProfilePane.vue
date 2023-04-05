<template>
    <div class="profile-pane">
        <v-list-item class="px-2" two-line>
            <v-list-item-avatar size="60" style="margin-left: 10px">
                <v-img :src="personalData.avatar"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
                <v-list-item-title>{{ personalData.name }}</v-list-item-title>
                <v-list-item-subtitle>{{ personalData.text }}</v-list-item-subtitle>
            </v-list-item-content>

            <v-btn icon
                   @click="personal"
            >
                <v-icon>mdi-chevron-right</v-icon>
            </v-btn>
        </v-list-item>

        <v-divider></v-divider>
        <v-divider></v-divider>

        <v-list
                nav
                dense
        >
            <v-col>
                <v-list-item link @click="updatePassword">
                    <v-list-item-icon>
                        <v-icon>mdi-lock-reset</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>修改密码</v-list-item-title>
                </v-list-item>
                <v-list-item link @click="exit">
                    <v-list-item-icon>
                        <v-icon>exit_to_app</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>退出</v-list-item-title>
                </v-list-item>
            </v-col>
        </v-list>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

import personalViewModel from '@/platform/vue/view/model/PersonalViewModel';
import PersonalData from '@/views/common/data/PersonalData';
import AppUtil from '@/platform/wap/util/AppUtil';
import RouterUtil from '@/common/vue/RouterUtil';


@Component({
    components: {},
})
export default class ProfilePane extends Vue {
    private personalData: PersonalData = personalViewModel.personalData;

    private exit(): void {
        AppUtil.logout();
    }

    private personal() {
        RouterUtil.toByPath('/personal.info');
    }

    private updatePassword() {
        RouterUtil.toByPath('/personal.update.password');
    }
}
</script>

<style lang="scss">
.profile-pane {

}
</style>
