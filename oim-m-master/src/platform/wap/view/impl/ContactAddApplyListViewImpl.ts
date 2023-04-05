import AbstractMaterial from '@/app/base/context/AbstractMaterial';
import ContactAddApplyListView from '@/app/com/main/module/business/contact/view/ContactAddApplyListView';
import RouterUtil from '@/common/vue/RouterUtil';

export default class ContactAddApplyListViewImpl extends AbstractMaterial implements ContactAddApplyListView {

    public setVisible(visible: boolean): void {
        if (visible) {
            RouterUtil.toByPath('/notice.contact.add.apply.list');
        }
    }

    public isVisible(): boolean {
        return RouterUtil.isPath('/notice.contact.add.apply.list');
    }
}
