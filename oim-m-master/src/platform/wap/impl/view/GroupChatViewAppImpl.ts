import GroupChatViewImpl from '@/platform/vue/view/impl/GroupChatViewImpl';
import Group from '@/app/com/main/module/business/group/bean/Group';
import RouterUtil from '@/common/vue/RouterUtil';


class GroupChatViewAppImpl extends GroupChatViewImpl {
    public setGroup(group: Group): void {
        super.setGroup(group);
        RouterUtil.toByPath('/chat.group')
    }
}

export default GroupChatViewAppImpl;
