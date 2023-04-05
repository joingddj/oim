import Path from '@/app/com/common/permission/Path';

class GroupMemberExtendPath {
    public service: string = 'manage';
    public addByUserIds: Path = new Path(this.service, '/v1/group/group.member/add.by.user.ids');
}

export default new GroupMemberExtendPath();
