import GroupChatViewImpl from '@/platform/vue/view/impl/GroupChatViewImpl';
import RouterUtil from '@/common/vue/RouterUtil';

export default class WapGroupChatViewImpl extends GroupChatViewImpl {

    public setVisible(visible: boolean): void {
        if (visible) {
            RouterUtil.toByPath('/chat.group');
        }
    }

    public isVisible(): boolean {
        return RouterUtil.isPath('/chat.group');
    }
}
