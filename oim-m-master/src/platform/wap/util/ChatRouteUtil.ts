import {Dictionary} from 'vue-router/types/router';
import routerManager from '@/router/RouterManager';

const router = routerManager.getRouter();

class ChatRouteUtil {
    public static toUserChat(userId: string) {
        const data: Dictionary<string> = {userId};
        const route = {path: '/chat.user', params: data};
        router.push(route).then((r) => {
        });
    }

    public static isUserChat(): boolean {
        const path = '/chat.user';
        const fullPath = (router.app && router.app.$route) ? router.app.$route.fullPath : '';
        return fullPath === path;
    }
}

export default ChatRouteUtil;
