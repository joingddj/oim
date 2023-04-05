<template>
    <div>
        <v-app-bar
            dark
            dense
            color="deep-purple primary"
        >
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
            <v-toolbar-title>消息</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
                <!-- <v-icon>mdi-magnify</v-icon>-->
            </v-btn>
        </v-app-bar>
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="no-message" v-show="!hasMessage">
                <div>
                    <img :src="noLogo"/>
                    <h4>{{ '没有消息' }}</h4>
                </div>
            </div>
            <div v-show="hasMessage">
                <div v-for="item of items">
                    <icon-item :data="item" :box="box" @on-selected="selected"
                               @on-context-menu='onItemContextMenu'></icon-item>
                </div>
            </div>
        </van-pull-refresh>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

import MessageRefreshPane from '@/views/module/message/MessageRefreshPane.vue';

import IconItem from '@/views/common/list/IconItem.vue';
import IconListPane from '../../common/list/IconListPane.vue';
import IconItemData from '../../common/list/IconItemData';
import messageListModel from '@/platform/vue/view/model/MessageListModel';
import IconItemBox from '@/views/common/list/IconItemBox';
import CommonIcon from '@/platform/common/web/CommonIcon';


@Component({
    components: {MessageRefreshPane, IconItem},
})
export default class MessageListPane extends Vue {
    private isLoading: boolean = false;
    private items: IconItemData[] = messageListModel.list;
    private box: IconItemBox = messageListModel.box;
    private hasMessage = true;
    private noLogo = CommonIcon.noLogo;

    public mounted() {
    }

    public loadUnread() {

    }

    public handleTopChange() {

    }

    private onRefresh() {
        setTimeout(() => {
            this.isLoading = false;
        }, 1000);
    }

    private onSelected(data: IconItemData) {
        this.$router.push({path: '/chat.user'});
    }

    private selected(data: IconItemData) {
        // no
    }

    private onItemContextMenu(data: IconItemData) {
        // 选中
    }
}
</script>

<style lang="scss">
.no-message {
    text-align: center;
    /*position: relative;*/

    > div {
        position: absolute;
        width: 100%;
        /*height: 180px;*/
        top: calc(50% - 100px);
        left: 0;
    }

    h4 {
        margin: 0;
        color: #818181;
    }
}
</style>
