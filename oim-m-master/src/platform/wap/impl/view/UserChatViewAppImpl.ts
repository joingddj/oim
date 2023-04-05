import UserChatViewImpl from '@/platform/vue/view/impl/UserChatViewImpl';
import User from '@/app/com/main/module/business/user/bean/User';
import ChatRouteUtil from '@/platform/wap/util/ChatRouteUtil';

class UserChatViewAppImpl extends UserChatViewImpl {

    public setUser(user: User): void {
        super.setUser(user);
        ChatRouteUtil.toUserChat(user.id);
    }
}

export default UserChatViewAppImpl;
