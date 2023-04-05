import BaseUtil from '@/app/lib/util/BaseUtil';

export default class BaseAllExplain {

    public baseSetName(items: any[], explains: any[], itemKeyField: string, explainKeyField: string, explainValueField: string, nameField: string, children: string): void {
        const map: Map<any, any> = new Map<any, any>();
        if (items && explains) {
            const own = this;

            const length = explains.length;
            for (let i = 0; i < length; i++) {
                const explain = explains[i];
                const kv = explain[explainKeyField];
                const vv = explain[explainValueField];
                let key: string = '';
                if (BaseUtil.isNotEmpty(kv)) {
                    key = kv.toString();
                }

                let value: string = '';
                if (BaseUtil.isNotEmpty(vv)) {
                    value = vv.toString();
                }
                map.set(key, value);
            }

            own.doSetName(items, map, itemKeyField, nameField, children);
        }
    }


    public doSetName(items: any[], explainMap: Map<any, any>, itemKeyField: string, nameField: string, children: string): void {
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
                const name = (explainMap) ? explainMap.get(key) : '';
                item[nameField] = name;

               // Object.defineProperty()
                if (children && '' != children) {
                    const nodes = item[children];
                    if (nodes) {
                        own.doSetName(nodes, explainMap, itemKeyField, nameField, children);
                    }
                }
            }
        }
    }
}