export default class PathUtil {

    public static merge(path1: string, path2: string): string {
        let path: string = '';
        if (path1 && path2) {
            const merge = '/';
            const end1: boolean = path1.endsWith(merge);
            const start2: boolean = path2.startsWith(merge);
            if (end1 && start2) {
                path = path1 + path2.substring(1);
            } else if (end1 || start2) {
                path = path1 + path2;
            } else {
                path = path1 + merge + path2;
            }
        } else if (path1 || path2) {
            path = (path1 ? path1 : '') + (path2 ? path2 : '');
        }
        return path;
    }

    public static prependLeadingSlash(path: string): string {
        const pre = '/';
        if (path && !path.startsWith(pre)) {
            return pre + path;
        } else {
            return path;
        }
    }
}
