<template>
    <v-card class="query-pane">
        <v-app-bar
            show-arrows
            dark
            dense
            color="deep-purple primary"
        >
            <back-button></back-button>
            <v-tabs
                v-model="model.tab"
            >
                <v-tabs-slider></v-tabs-slider>
                <v-tab
                    :key="type.GroupJoinApplyListView"
                >
                    群申请加入验证
                </v-tab>
                <v-tab
                    :key="type.GroupInviteApplyListView"
                >
                    群邀请加入验证
                </v-tab>
                <v-tab
                    :key="type.GroupInviteeApplyListView"
                >
                    群被邀请加入处理
                </v-tab>
            </v-tabs>
        </v-app-bar>
        <div class="center pane">
            <v-tabs-items :value="model.tab">
                <v-tab-item
                    :value="type.GroupJoinApplyListView"
                >
                    <group-join-apply-list-pane></group-join-apply-list-pane>
                </v-tab-item>
                <v-tab-item
                    :value="type.GroupInviteApplyListView"
                >
                    <group-invite-apply-list-pane></group-invite-apply-list-pane>
                </v-tab-item>
                <v-tab-item
                    :value="type.GroupInviteeApplyListView"
                >
                    <group-invitee-apply-list-pane></group-invitee-apply-list-pane>
                </v-tab-item>
            </v-tabs-items>
        </div>
    </v-card>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';

import ApplyHandleType from '@/platform/wap/view/data/ApplyHandleType';
import groupHandlerTab from '@/platform/wap/view/data/GroupHandlerTab';

import GroupJoinApplyListPane from '@/views/module/notice/GroupJoinApplyListPane.vue';
import GroupInviteApplyListPane from '@/views/module/notice/GroupInviteApplyListPane.vue';
import GroupInviteeApplyListPane from '@/views/module/notice/GroupInviteeApplyListPane.vue';


@Component({
    components: {
        BackButton,
        GroupJoinApplyListPane,
        GroupInviteApplyListPane,
        GroupInviteeApplyListPane,
    },
})
export default class GroupHandlerTabPane extends Vue {
    private type = ApplyHandleType;
    private model = groupHandlerTab;

    public mounted() {
        // no
    }
}
</script>

<style lang="scss" scoped>
.pane {
    overflow-y: auto;
}

.query-pane {
    width: 100%;
    height: 100%;
    overflow: hidden;

    .center {
        height: calc(100% - 45px);
        overflow-y: auto;
    }
}
</style>
