import BaseItemExplain from '@/zone/common/explain/BaseItemExplain';
import UserExtendClient from '@/zone/general/work/core/main/client/UserExtendClient';

class UserDataExplain extends BaseItemExplain {

    protected loadExplain(
        items: any[],
        keys: any[],
        itemKeyField: string,
        explainKeyField: string,
        handle: (item: any, explain: any) => void,
        children: string): void {
        const own = this;
        UserExtendClient.listByIds(keys, (result) => {
            const info = result.info;
            const body = result.body;
            if (info && info.success && body) {
                const list: any[] = body.items;
                own.baseSetName(items, list, itemKeyField, explainKeyField, handle, children);
            }
        });
    }
}

export default new UserDataExplain();
