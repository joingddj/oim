import PathUtil from '@/app/com/common/util/PathUtil';

class PermissionBox {

    private map: Map<string, string> = new Map<string, string>();
    private useService: boolean = true;

    public setUseService(useService: boolean): void {
        this.useService = useService;
    }

    public isUseService(): boolean {
        return this.useService;
    }

    public put(service: string, path: string) {
        if (!this.isUseService()) {
            service = 'all';
        }
        const key = PathUtil.prependLeadingSlash(PathUtil.merge(service, path));
        if (key) {
            this.map.set(key, '');
        }
    }

    public has(service: string, path: string): boolean {
        let has = false;
        if (!this.isUseService()) {
            service = 'all';
        }
        const key = PathUtil.prependLeadingSlash(PathUtil.merge(service, path));
        has = this.map.has(key);
        return has;
    }

    public clear(): void {
        this.map.clear();
    }
}

export default new PermissionBox();
