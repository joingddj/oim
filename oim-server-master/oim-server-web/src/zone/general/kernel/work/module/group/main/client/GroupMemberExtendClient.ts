import BaseClient from '@/app/com/common/client/BaseClient';

import GroupMemberExtendPath from '@/zone/general/kernel/work/module/group/main/path/GroupMemberExtendPath';


class GroupMemberExtendClient extends BaseClient {

    public addByUserIds(groupId: string, userIds: string[], back: (data: any) => void) {
        this.post(GroupMemberExtendPath.addByUserIds.getUrl(), {groupId, userIds}, back, true);
    }
}

export default new GroupMemberExtendClient();
