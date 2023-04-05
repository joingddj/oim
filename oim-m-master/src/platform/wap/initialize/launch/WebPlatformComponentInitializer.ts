import AppContext from '@/app/base/context/AppContext';
import Prompter from '@/app/com/client/component/Prompter';
import WebPromptHandlerImpl from '@/platform/wap/impl/WebPromptHandlerImpl';
import WorkViewEnum from '@/app/com/common/view/WorkViewEnum';
import WebPlatformFileIconInitializer from '@/platform/common/web/initialize/launch/more/WebPlatformFileIconInitializer';
import WapUserChatViewImpl from '@/platform/wap/impl/view/WapUserChatViewImpl';
import WapGroupChatViewImpl from '@/platform/wap/impl/view/WapGroupChatViewImpl';
import WapContactInfoViewImpl from '@/platform/wap/impl/view/WapContactInfoViewImpl';
import WapGroupInfoViewImpl from '@/platform/wap/impl/view/WapGroupInfoViewImpl';
import ContactListPaneViewImpl from '@/platform/vue/view/impl/ContactListPaneViewImpl';
import ContactListPaneViewAppImpl from '@/platform/wap/impl/view/ContactListPaneViewAppImpl';
import WapMessageAllUnreadViewImpl from '@/platform/wap/impl/view/WapMessageAllUnreadViewImpl';
import ContactAddApplyListViewImpl from '@/platform/wap/view/impl/ContactAddApplyListViewImpl';
import GroupInviteApplyListViewImpl from '@/platform/wap/view/impl/GroupInviteApplyListViewImpl';
import GroupInviteeApplyListViewImpl from '@/platform/wap/view/impl/GroupInviteeApplyListViewImpl';
import GroupJoinApplyListViewImpl from '@/platform/wap/view/impl/GroupJoinApplyListViewImpl';
import LaunchInitializer from '@/app/base/initialize/LaunchInitializer';

export default class WebPlatformComponentInitializer extends LaunchInitializer {

    public getOrder(): number {
        return 0;
    }

    public initialize(): void {
        this.initializeHandle(this.appContext);
    }

    public getKey(): string {
        const own: object = this;
        return own.constructor.name;
    }

    public initializeHandle(appContext: AppContext): void {
        const prompter: Prompter = appContext.getMaterial(Prompter);
        prompter.setPromptHandler(new WebPromptHandlerImpl());
        this.initializeView(appContext);
        this.initializeFileIcon(appContext);
    }

    public initializeView(appContext: AppContext) {

        appContext.putViewImpl(WorkViewEnum.ContactListPaneView, ContactListPaneViewAppImpl);
        appContext.putViewImpl(WorkViewEnum.UserChatView, WapUserChatViewImpl);
        appContext.putViewImpl(WorkViewEnum.GroupChatView, WapGroupChatViewImpl);
        appContext.putViewImpl(WorkViewEnum.ContactInfoView, WapContactInfoViewImpl);
        appContext.putViewImpl(WorkViewEnum.GroupInfoView, WapGroupInfoViewImpl);
        appContext.putViewImpl(WorkViewEnum.MessageAllUnreadView, WapMessageAllUnreadViewImpl);
        appContext.putViewImpl(WorkViewEnum.GroupInviteApplyListView, GroupInviteApplyListViewImpl);
        appContext.putViewImpl(WorkViewEnum.GroupInviteeApplyListView, GroupInviteeApplyListViewImpl);
        appContext.putViewImpl(WorkViewEnum.GroupJoinApplyListView, GroupJoinApplyListViewImpl);
        appContext.putViewImpl(WorkViewEnum.ContactAddApplyListView, ContactAddApplyListViewImpl);
    }

    public initializeFileIcon(appContext: AppContext) {
        const fileIconInitializer: WebPlatformFileIconInitializer = new WebPlatformFileIconInitializer();
        fileIconInitializer.initialize(appContext);
    }
}
