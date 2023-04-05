import BaseRouteConfig from '@/router/BaseRouteConfig';

export default class RouteLoader {

    public load(): BaseRouteConfig[] {
        const own = this;

        const array: BaseRouteConfig[] = [];
        let requireContext = require.context(
            './module/', // 在当前目录下查找
            true, // 历子文件夹
            /\.ts$/, // 正则匹配 以 .ts结尾的文件
        );
        requireContext.keys().forEach((fileName) => {
            const component = requireContext(fileName);
            const name = fileName.replace(/^\.\/(.*)\.\w+$/, '$1');
            const v = component.default;
            if (v instanceof BaseRouteConfig) {
                array.push(v);
            }
        });

        requireContext = require.context(
            '../zone/', // 目录下查找
            true, // 历子文件夹
            /\.ts$/, // 正则匹配 以 .ts结尾的文件
        );
        requireContext.keys().forEach((fileName) => {
            const component = requireContext(fileName);
            const name = fileName.replace(/^\.\/(.*)\.\w+$/, '$1');
            const v = component.default;
            if (v instanceof BaseRouteConfig) {
                array.push(v);
            }
        });
        return array;
    }
}
