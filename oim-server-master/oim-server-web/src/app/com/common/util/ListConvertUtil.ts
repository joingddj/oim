class ListConvertUtil {
    public static convert<R, T>(list: T[], c: (data: T) => R): R[] {
        const items: any[] = [];
        if (list) {
            for (const d of list) {
                const v = c(d);
                items.push(v);
            }
        }
        return items;
    }
}

export default new ListConvertUtil();
