import BaseClient from '@/app/com/common/client/BaseClient';
import Page from '@/app/com/common/data/Page';
import UserExtendPath from '@/zone/general/work/core/main/path/UserExtendPath';


class UserExtendClient extends BaseClient {

    public list(query: any, page: Page, back: (data: any) => void) {
        this.post(UserExtendPath.list.getUrl(), {query, page}, back, true);
    }

    public listByIds(ids: any[], back: (data: any) => void) {
        this.post(UserExtendPath.listByIds.getUrl(), {ids}, back, true);
    }

    public queryList(query: any, page: Page, back: (data: any) => void) {
        this.post(UserExtendPath.queryList.getUrl(), {query, page}, back, true);
    }
}

export default new UserExtendClient();
