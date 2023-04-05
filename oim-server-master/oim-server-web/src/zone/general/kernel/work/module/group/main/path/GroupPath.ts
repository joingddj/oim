import Path from '@/app/com/common/permission/Path';

class GroupPath {
    public service: string = 'manage';
    public list: Path = new Path(this.service, '/v1/group/group/list');
    public add: Path = new Path(this.service, '/v1/group/group/add');
    public update: Path = new Path(this.service, '/v1/group/group/update');
    public addOrUpdate: Path = new Path(this.service, '/v1/group/group/add.or.update');
    public getById: Path = new Path(this.service, '/v1/group/group/get.by.id');
    public deleteById: Path = new Path(this.service, '/v1/group/group/delete.by.id');
}

export default new GroupPath();
