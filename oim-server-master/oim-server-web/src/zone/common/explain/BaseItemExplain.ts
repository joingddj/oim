import BaseUtil from '@/app/lib/util/BaseUtil';

export default abstract class BaseItemExplain {

    protected abstract loadExplain(items: any[],
                                   keys: any[],
                                   itemKeyField: string,
                                   explainKeyField: string,
                                   handle: (item: any, explain: any) => void,
                                   children: string): void;

    public setExplain(items: any[],
                      itemKeyField: string,
                      explainKeyField: string,
                      handle: (item: any, explain: any) => void,
                      children: string): void {
        const keys: any[] = [];
        if (items) {
            const own = this;


            const length = items.length;
            for (let i = 0; i < length; i++) {
                const item = items[i];
                const key = item[itemKeyField];
                if (BaseUtil.isNotEmpty(key)) {
                    keys.push(key);
                }
            }
            own.loadExplain(items, keys, itemKeyField, explainKeyField, handle, children);
        }
    }

    protected baseSetName(
        items: any[],
        explains: any[],
        itemKeyField: string,
        explainKeyField: string,
        handle: (item: any, explain: any) => void,
        children: string): void {
        const map: Map<any, any> = new Map<any, any>();
        if (items && explains) {
            const own = this;

            const length = explains.length;
            for (let i = 0; i < length; i++) {
                const explain = explains[i];
                const kv = explain[explainKeyField];
                let key: string = '';
                if (BaseUtil.isNotEmpty(kv)) {
                    key = kv.toString();
                }
                map.set(key, explain);
            }
            own.doSetName(items, map, itemKeyField, handle, children);
        }
    }


    protected doSetName(
        items: any[],
        explainMap: Map<any, any>,
        itemKeyField: string,
        handle: (item: any, explain: any) => void,
        children: string): void {
        if (items && explainMap) {
            const own = this;


            const length = items.length;
            for (let i = 0; i < length; i++) {
                const item = items[i];
                const value = item[itemKeyField];
                let key: string = '';
                if (BaseUtil.isNotEmpty(value)) {
                    key = value.toString();
                }
                const explain = (explainMap) ? explainMap.get(key) : {};
                handle(item, explain);
                if (children && '' != children) {
                    const nodes = item[children];
                    if (nodes) {
                        own.doSetName(nodes, explainMap, itemKeyField, handle, children);
                    }
                }
            }
        }
    }


    public setExplainValue(data: any, explain: any, dataFieldName: string, explainFieldName: string) {
        if (data && explain && dataFieldName && explainFieldName) {
            data[dataFieldName] = explain[explainFieldName];
        }
    }

    public setValue(data: any, fieldName: string, value: any) {
        if (data && fieldName) {
            data[fieldName] = value;
        }
    }
}