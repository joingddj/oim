import {RouteConfig} from 'vue-router';

class RouteConfigBox {
    public routers: RouteConfig[] = [];

    public constructor() {
        this.routers = [
            {
                path: '/',
                redirect: '/login',
            },
            {
                path: '/login',
                name: 'login',
                component: () => import('@/views/Login.vue'),
            },
            {
                path: '/register',
                name: 'register',
                component: () => import('@/views/Register.vue'),
            },
            {
                path: '/main',
                name: 'main',
                component: () => import('@/views/Main.vue'),
            },
            {
                path: '/personal.info',
                name: 'personalInfo',
                component: () => import('@/views/module/personal/PersonalInfoPane.vue'),
            },
            {
                path: '/personal.update.info',
                name: 'personalUpdateInfo',
                component: () => import('@/views/module/personal/PersonalUpdateInfoPane.vue'),
            },
            {
                path: '/personal.update.password',
                name: 'personalUpdatePassword',
                component: () => import('@/views/module/personal/PersonalUpdatePasswordPane.vue'),
            },
            {
                path: '/query',
                name: 'query',
                component: () => import('@/views/main/query/QueryPane.vue'),
            },
            {
                path: '/user.find',
                name: 'userFind',
                component: () => import('@/views/module/user/find/UserFindPane.vue'),
            },
            {
                path: '/chat.user',
                name: 'userChat',
                component: () => import('@/views/module/chat/UserChatPane.vue'),
            },
            {
                path: '/chat.user.history',
                name: 'userChatHistory',
                component: () => import('@/views/module/chat/UserChatHistoryPane.vue'),
            },
            {
                path: '/chat.group',
                name: 'groupChat',
                component: () => import('@/views/module/chat/GroupChatPane.vue'),
            },
            {
                path: '/chat.group.history',
                name: 'groupChatHistory',
                component: () => import('@/views/module/chat/GroupChatHistoryPane.vue'),
            },
            {
                path: '/contact.info/:userId',
                name: 'userInfo',
                component: () => import('@/views/module/contact/info/ContactInfoViewPane.vue'),
                props: true,
            },
            {
                path: '/group.info/:groupId',
                name: 'groupInfo',
                component: () => import('@/views/module/group/info/GroupInfoViewPane.vue'),
                props: true,
            },
            {
                path: '/group.info.member.list/:groupId',
                name: 'groupMemberList',
                component: () => import('@/views/module/group/member/GroupMemberListPane.vue'),
                props: true,
            },
            {
                path: '/group.find',
                name: 'groupFind',
                component: () => import('@/views/module/group/find/GroupFindPane.vue'),
                props: true,
            },
            {
                path: '/notice.contact.add.apply.list',
                name: 'noticeContactAddApplyList',
                component: () => import('@/views/module/notice/ContactAddApplyListPane.vue'),
                props: true,
            },
            {
                path: '/notice.group.handle',
                name: 'noticeGroupHandle',
                component: () => import('@/views/module/notice/GroupHandlerTabPane.vue'),
                props: true,
            },

        ];
        this.initialize();
    }

    public add(route: RouteConfig): void {
        this.routers.push(route);
    }

    public getRouters(): RouteConfig[] {
        return this.routers;
    }

    private initialize(): void {
        // no
    }
}

export default new RouteConfigBox();
