import BaseClient from '@/app/com/common/client/BaseClient';
import Page from '@/app/com/common/data/Page';
import GroupMember from '@/zone/general/kernel/work/module/group/main/entity/GroupMember';
import GroupMemberPath from '@/zone/general/kernel/work/module/group/main/path/GroupMemberPath';


class GroupMemberClient extends BaseClient {

    public list(query: any, page: Page, back: (data: any) => void) {
        this.post(GroupMemberPath.list.getUrl(), {query, page}, back, true);
    }

    public add(info: GroupMember, back: (data: any) => void) {
        this.post(GroupMemberPath.add.getUrl(), info, back, true);
    }

    public update(info: GroupMember, back: (data: any) => void) {
        this.post(GroupMemberPath.update.getUrl(), info, back, true);
    }

    public addOrUpdate(info: GroupMember, back: (data: any) => void) {
        this.post(GroupMemberPath.addOrUpdate.getUrl(), info, back, true);
    }

    public getById(id: string, back: (data: any) => void) {
        this.post(GroupMemberPath.getById.getUrl(), {id}, back, true);
    }

    public deleteById(id: string, back: (data: any) => void) {
        this.post(GroupMemberPath.deleteById.getUrl(), {id}, back, true);
    }
}

export default new GroupMemberClient();
