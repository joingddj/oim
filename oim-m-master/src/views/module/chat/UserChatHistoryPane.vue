<template>
    <div class="chat-pane">
        <v-card
            class="mx-auto"
            elevation="8"
            dark
            color="primary"
        >
            <div>
                <v-list-item
                    @click=""
                >
                    <back-button></back-button>
                    <v-toolbar-title>{{ chatViewModel.info.name }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                </v-list-item>
            </div>
        </v-card>
        <div ref="messageListPane" class="center scroll-wrapper scrollbar-dynamic">
            <ReadPane :items="list"
                      @on-refresh="nextPage"
            ></ReadPane>
        </div>
        <div class="bottom">
            <div class="text-center" style="width: 100%">
                <v-pagination
                    v-model="page.number"
                    :length="page.totalPage"
                    @input="pageChange"
                    circle
                ></v-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';
import ReadPane from '@/views/component/chat/ReadPane.vue';
import app from '@/app/App';
import UserChatQuery from '@/app/com/main/module/business/chat/data/UserChatQuery';
import PersonalBox from '@/app/com/main/module/business/personal/box/PersonalBox';
import UserChatDataController from '@/app/com/main/module/business/chat/controller/UserChatDataController';
import Page from '@/app/com/common/data/Page';
import MessageContentWrap from '@/common/vue/data/content/impl/message/MessageContentWrap';
import userChatViewModel from '@/platform/vue/view/model/UserChatViewModel';
import ContentItemHandleService from '@/common/web/content/service/ContentItemHandleService';
import ContentWrapUtil from '@/common/vue/data/content/util/ContentWrapUtil';
import groupChatViewModel from '@/platform/vue/view/model/GroupChatViewModel';

@Component({
    components: {
        BackButton,
        ReadPane,
    },
})
export default class UserChatHistoryPane extends Vue {
    private chatViewModel = userChatViewModel;
    private list: MessageContentWrap[] = [];
    private page: Page = new Page();
    private query: UserChatQuery = new UserChatQuery();
    private chatData = userChatViewModel.viewData;

    public mounted() {
        this.queryList();
    }

    private nextPage() {
        const own = this;
        const page = this.page;
        if (page.number < page.totalPage) {
            page.number++;
            own.pageChange();
        }
    }

    public pageChange() {
        this.queryList();
    }

    public queryList() {
        const own = this;
        const page = this.page;
        const query: UserChatQuery = this.query;
        const pb: PersonalBox = app.appContext.getMaterial(PersonalBox);
        const userId = this.chatData.key;
        query.receiveUserId = userId;
        query.sendUserId = pb.getUserId();

        const service: ContentItemHandleService = app.appContext.getMaterial(ContentItemHandleService);


        const userChatController: UserChatDataController = app.appContext.getMaterial(UserChatDataController);

        const back = (p: Page, contents: MessageContentWrap[]) => {

            if (service) {
                for (const cd of contents) {
                    service.handleContent(cd.content);
                }
            }
            ContentWrapUtil.sort(contents);
            own.setList(p, contents);
        };
        userChatController.queryList(query, page, back);
    }

    public setList(page: Page, items: MessageContentWrap[]) {
        this.page = page;
        this.list = items;
    }
}
</script>

<style lang="scss">

</style>
