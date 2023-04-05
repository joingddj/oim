import GroupInfoViewImpl from '@/platform/vue/view/impl/GroupInfoViewImpl';
import mainViewData from '@/platform/wap/view/data/MainViewData';
import mainBaseTabs from '@/platform/wap/view/data/MainBaseTabs';
import RouterUtil from '@/common/vue/RouterUtil';

export default class WapGroupInfoViewImpl extends GroupInfoViewImpl {

    public setVisible(visible: boolean): void {
        if (visible) {
            const path = '/group.info/';
            RouterUtil.toByPath(path);
        }
    }

    public isVisible(): boolean {
        const path = '/group.info/';
        return RouterUtil.isPath(path);
    }
}
