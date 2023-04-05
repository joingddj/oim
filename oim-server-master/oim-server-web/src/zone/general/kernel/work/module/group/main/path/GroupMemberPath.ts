import Path from '@/app/com/common/permission/Path';

class GroupMemberPath {
    public service: string = 'manage';
    public list: Path = new Path(this.service, '/v1/group/group.member/list');
    public add: Path = new Path(this.service, '/v1/group/group.member/add');
    public update: Path = new Path(this.service, '/v1/group/group.member/update');
    public addOrUpdate: Path = new Path(this.service, '/v1/group/group.member/add.or.update');
    public getById: Path = new Path(this.service, '/v1/group/group.member/get.by.id');
    public deleteById: Path = new Path(this.service, '/v1/group/group.member/delete.by.id');
}

export default new GroupMemberPath();
