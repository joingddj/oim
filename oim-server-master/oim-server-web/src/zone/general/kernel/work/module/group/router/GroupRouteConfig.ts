import BaseRouteConfig from '@/router/BaseRouteConfig';
import GroupPath from '@/zone/general/kernel/work/module/group/main/path/GroupPath';
import GroupMemberPath from '@/zone/general/kernel/work/module/group/main/path/GroupMemberPath';

class GroupRouteConfig extends BaseRouteConfig {

    public getKey(): string {
        return 'system-manage';
    }

    public getRoutes(): any[] {
        const array: any[] = [
            {
                path: '/group',
                component: () => import('@/views/Main.vue'),
                meta: {
                    title: '群',
                },
                children: [
                    {
                        path: 'group.list',
                        name: 'group/group/list',
                        component: () => import('@/zone/general/kernel/work/module/group/view/GroupList.vue'),
                        meta: {
                            title: '群列表',
                            url: GroupPath.list,
                        },
                    }
                ],
            },
        ];
        return array;
    }

    public getTitle(): string {
        return '系统管理';
    }
}

export default new GroupRouteConfig();
