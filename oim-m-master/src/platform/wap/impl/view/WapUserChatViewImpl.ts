import UserChatViewImpl from '@/platform/vue/view/impl/UserChatViewImpl';
import ChatRouteUtil from '@/platform/wap/util/ChatRouteUtil';

export default class WapUserChatViewImpl extends UserChatViewImpl {

    public isVisible(): boolean {
        return ChatRouteUtil.isUserChat();
    }

    public setVisible(visible: boolean): void {
        if (visible) {
            ChatRouteUtil.toUserChat('');
        } else {
            // no
        }
    }
}
