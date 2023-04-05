import AbstractMaterial from '@/app/base/context/AbstractMaterial';
import GroupInviteApplyListView from '@/app/com/main/module/business/group/view/GroupInviteApplyListView';
import RouterUtil from '@/common/vue/RouterUtil';
import groupHandlerTab from '@/platform/wap/view/data/GroupHandlerTab';
import ApplyHandleType from '@/platform/wap/view/data/ApplyHandleType';

export default class GroupInviteApplyListViewImpl extends AbstractMaterial implements GroupInviteApplyListView {
    private handlerTab = groupHandlerTab;

    public isVisible(): boolean {
        const showTab = this.handlerTab.tab === ApplyHandleType.GroupInviteApplyListView;
        const showPath = RouterUtil.isPath('/notice.group.handle');
        return showTab && showPath;
    }

    public setVisible(visible: boolean): void {
        // no
        if (visible) {
            RouterUtil.toByPath('/notice.group.handle');
            this.handlerTab.tab = ApplyHandleType.GroupInviteApplyListView;
        }
    }
}
