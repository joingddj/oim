import NodeData from '@/views/common/list/NodeData';
import PinYinUtil from '@/platform/wap/util/PinYinUtil';
import ItemData from '@/views/common/list/ItemData';
import ListIndexData from '@/platform/wap/impl/view/ListIndexData';
import ContactListPaneViewImpl from '@/platform/vue/view/impl/ContactListPaneViewImpl';

class ContactListPaneViewAppImpl extends ContactListPaneViewImpl {

    protected keyIndexNodeMap: Map<string, NodeData> = new Map<string, NodeData>();
    protected keyIndexNodes: NodeData[] = ListIndexData.userNodes;
    protected keyIndexes = ListIndexData.userIndexes;

    public addOrUpdateItem(categoryId: string, itemId: string, name: string, avatar: string, gray: boolean): void {
        super.addOrUpdateItem(categoryId, itemId, name, avatar, gray);
        this.update();
        // const item: ItemData = this.listBox.getItemByKey(itemId);
        // const map = this.keyIndexNodeMap;
        // const key: string = PinYinUtil.getFirstText(name);
        //
        //
        // for (const indexData of map.values()) {
        //     const it = this.getItem(indexData.items, itemId);
        //     if (it) {
        //         const index = indexData.items.indexOf(it);
        //         if (index > -1) {
        //             indexData.items.splice(index, 1);
        //         }
        //     }
        // }
        //
        // let indexData = map.get(key);
        // if (!indexData) {
        //     indexData = new NodeData();
        //     indexData.key = key;
        //     map.set(key, indexData);
        //
        //     this.keyIndexNodes.push(indexData);
        // }
        //
        // if (!this.has(indexData.items, item)) {
        //     indexData.items.push(item);
        // }
    }

    public removeCategoryMember(categoryId: string, itemId: string): void {
        const item: ItemData = this.listBox.getItemByKey(itemId);
        this.listBox.deleteItem(categoryId, itemId);
        this.update();
        // const map = this.keyIndexNodeMap;
        // if (item) {
        //     const key: string = PinYinUtil.getFirstText(item.name);
        //     const indexData = map.get(key);
        //     if (indexData) {
        //         if (indexData.items) {
        //             const index = indexData.items.indexOf(item);
        //             if (index > -1) {
        //                 indexData.items.splice(index, 1);
        //             }
        //         }
        //     }
        // }
    }

    public has(items: ItemData[], item: ItemData): boolean {
        const has = false;
        if (items && item) {
            for (const d of items) {
                if (d.key === item.key) {
                    return true;
                }
            }
        }
        return has;
    }

    public getItem(items: ItemData[], key: string): ItemData {
        if (items && key) {
            for (const d of items) {
                if (d.key === key) {
                    return d;
                }
            }
        }
        return undefined as any;
    }

    private update(): void {
        const allNodes = this.listBox.nodes;
        const keyIndexNodeMap = this.keyIndexNodeMap;
        const keyIndexNodes = this.keyIndexNodes;
        const keyIndexes = this.keyIndexes;

        keyIndexNodeMap.clear();
        keyIndexNodes.length = 0;
        // if (length > 0) {
        //     keyIndexNodes.slice(0, length - 1);
        // }

        for (const nodeData of allNodes) {
            if (nodeData && nodeData.items) {
                for (const item of nodeData.items) {
                    const key: string = PinYinUtil.getFirstText(item.name);
                    let indexData = keyIndexNodeMap.get(key);
                    if (!indexData) {
                        indexData = new NodeData();
                        indexData.key = key;
                        keyIndexNodeMap.set(key, indexData);
                        keyIndexNodes.push(indexData);
                    }

                    indexData.items.push(item);
                }
            }
        }

        keyIndexNodes.sort((a, b) => {
            const ak = a.key;
            const bk = b.key;
            return ak.localeCompare(bk);
        });

        keyIndexes.length = 0;

        for (const n of keyIndexNodes) {
            keyIndexes.push(n.key);
        }
    }
}

export default ContactListPaneViewAppImpl;
