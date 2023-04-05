export default class FieldUtil {

    public static setField(list: any[], field: string, value: any, children: string) {
        if (list) {
            const length = list.length;
            for (let i = 0; i < length; i++) {
                const item = list[i];
                item[field] = value;
                if (children && '' != children) {
                    const nodes = item[children];
                    if (nodes) {
                        FieldUtil.setField(nodes, field, value, children);
                    }
                }
            }
        }
    }

    public static setFields(list: any[], fields: string[], value: any, children: string) {
        if (list && fields) {
            const length = list.length;
            for (let i = 0; i < length; i++) {
                const item = list[i];
                for (const field of fields) {
                    item[field] = value;
                }
                if (children && '' != children) {
                    const nodes = item[children];
                    if (nodes) {
                        FieldUtil.setFields(nodes, fields, value, children);
                    }
                }
            }
        }
    }
}