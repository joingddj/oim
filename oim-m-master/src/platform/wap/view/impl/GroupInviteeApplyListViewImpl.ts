import AbstractMaterial from '@/app/base/context/AbstractMaterial';
import GroupInviteeApplyListView from '@/app/com/main/module/business/group/view/GroupInviteeApplyListView';
import RouterUtil from '@/common/vue/RouterUtil';
import groupHandlerTab from '@/platform/wap/view/data/GroupHandlerTab';
import ApplyHandleType from '@/platform/wap/view/data/ApplyHandleType';


export default class GroupInviteeApplyListViewImpl extends AbstractMaterial implements GroupInviteeApplyListView {

    private handlerTab = groupHandlerTab;

    public isVisible(): boolean {
        const showTab = this.handlerTab.tab === ApplyHandleType.GroupInviteeApplyListView;
        const showPath = RouterUtil.isPath('/notice.group.handle');
        return showTab && showPath;
    }

    public setVisible(visible: boolean): void {
        // no
        if (visible) {
            RouterUtil.toByPath('/notice.group.handle');
            this.handlerTab.tab = ApplyHandleType.GroupInviteeApplyListView;
        }
    }
}
