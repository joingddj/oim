import BaseClient from '@/app/com/common/client/BaseClient';
import Page from '@/app/com/common/data/Page';
import Group from '@/zone/general/kernel/work/module/group/main/entity/Group';
import GroupPath from '@/zone/general/kernel/work/module/group/main/path/GroupPath';


class GroupClient extends BaseClient {

    public list(query: any, page: Page, back: (data: any) => void) {
        this.post(GroupPath.list.getUrl(), {query, page}, back, true);
    }

    public add(info: Group, back: (data: any) => void) {
        this.post(GroupPath.add.getUrl(), info, back, true);
    }

    public update(info: Group, back: (data: any) => void) {
        this.post(GroupPath.update.getUrl(), info, back, true);
    }

    public addOrUpdate(info: Group, back: (data: any) => void) {
        this.post(GroupPath.addOrUpdate.getUrl(), info, back, true);
    }

    public getById(id: string, back: (data: any) => void) {
        this.post(GroupPath.getById.getUrl(), {id}, back, true);
    }

    public deleteById(id: string, back: (data: any) => void) {
        this.post(GroupPath.deleteById.getUrl(), {id}, back, true);
    }
}

export default new GroupClient();
