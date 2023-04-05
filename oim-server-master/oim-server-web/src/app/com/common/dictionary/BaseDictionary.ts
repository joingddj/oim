import BaseUtil from '@/app/lib/util/BaseUtil';

export default class BaseDictionary {
    private map: Map<string, Map<string, Map<string, string>>> = new Map<string, Map<string, Map<string, string>>>();

    public getPropertyOrCreateMap(category: string, property: string): Map<string, string> {
        const own = this;
        let categoryMap = own.map.get(category);
        if (!categoryMap) {
            categoryMap = new Map<string, Map<string, string>>();
            own.map.set(category, categoryMap);
        }

        let propertyMap = categoryMap.get(property);
        if (!propertyMap) {
            propertyMap = new Map<string, string>();
            categoryMap.set(property, propertyMap);
        }
        return propertyMap;
    }

    public put(category: string, property: string, value: string, name: string) {
        const own = this;
        const propertyMap = own.getPropertyOrCreateMap(category, property);
        propertyMap.set(value, name);
    }

    public getName(category: string, property: string, value: string) {
        const own = this;
        const categoryMap = own.map.get(category);
        const propertyMap = (categoryMap) ? categoryMap.get(property) : null;
        const name = (propertyMap) ? propertyMap.get(value) : '';
        return name;
    };

    public setName(list: any[], category: string, property: string, valueField: string, nameField: string, children: string) {
        if (list) {
            const own = this;

            const categoryMap = own.map.get(category);
            const propertyMap = (categoryMap) ? categoryMap.get(property) : null;

            const length = list.length;
            for (let i = 0; i < length; i++) {
                const item = list[i];
                const value = item[valueField];
                let key: string = '';
                if (BaseUtil.isNotEmpty(value)) {
                    key = value.toString();
                }
                const name = (propertyMap) ? propertyMap.get(key) : '';
                item[nameField] = name;
                if (children && '' != children) {
                    const nodes = item[children];
                    if (nodes) {
                        own.setName(nodes, category, property, valueField, nameField, children);
                    }
                }
            }
        }
    }
}