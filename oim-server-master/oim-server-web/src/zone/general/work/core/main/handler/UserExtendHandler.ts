import Page from '@/app/com/common/data/Page';
import UserExtendClient from '@/zone/general/work/core/main/client/UserExtendClient';

class UserExtendHandler {

    public list(query: any, page: Page, back: (data: any) => void) {
        UserExtendClient.list(query, page, back);
    }

    public listByIds(ids: any[], back: (info: any, list: any[]) => void) {
        const ab = (data: any) => {
            back(data.info, data.body.items);
        };
        UserExtendClient.listByIds(ids, ab);
    }
}

export default new UserExtendHandler();
