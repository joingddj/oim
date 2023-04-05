import Path from '@/app/com/common/permission/Path';

class UserExtendPath {
    public service: string = 'manage';
    public list: Path = new Path(this.service, '/v1/user/info/list');
    public listByIds: Path = new Path(this.service, '/v1/user/info/list.by.ids');
    public queryList: Path = new Path(this.service, '/v1/user/info/query.list');
}

export default new UserExtendPath();
