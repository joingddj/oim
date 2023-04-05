import {RouteConfig} from 'vue-router';

import BaseRouteConfig from '@/router/BaseRouteConfig';
// import module from '@/router/ModuleImport';
import RouteLoader from '@/router/RouteLoader';


class RouteConfigBox {
    public routers: RouteConfig[] = [];
    public configs: BaseRouteConfig[] = [];

    private routeLoader: RouteLoader = new RouteLoader();

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
        ];
        this.initialize();
    }

    public add(route: RouteConfig): void {
        this.routers.push(route);
        // GroupMemberListPane
    }

    public getRouters(): RouteConfig[] {
        return this.routers;
    }

    public getRouteConfigs(): BaseRouteConfig[] {
        return this.configs;
    }

    public addRouteConfig(config: BaseRouteConfig) {
        this.configs.push(config);
        for (const r of config.getRoutes()) {
            this.add(r as RouteConfig);
        }
    }

    private initialize(): void {
        const own = this;
        // this.add(routeConfig);

        const array = own.routeLoader.load();
        for (const m of array) {
            const rs = m.getRoutes();

            this.addRouteConfig(m);
        }
    }
}

export default new RouteConfigBox();
