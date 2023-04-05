<template>
    <div>
        <van-index-bar :index-list="indexList">
            <template v-for="(v, i) in list">
                <van-index-anchor :index="v.key"/>
                <template v-for="(item,  j) in v.items">
                    <item-pane @on-selected="onOpenSend" :data="item"></item-pane>
                </template>
            </template>
        </van-index-bar>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import ItemPane from '@/views/common/list/ItemPane.vue';
import ItemData from '@/views/common/list/ItemData';
import NodeData from '@/views/common/list/NodeData';
import ListIndexData from '@/platform/wap/impl/view/ListIndexData';
import RouterUtil from '@/common/vue/RouterUtil';

@Component({
    components: {
        ItemPane
    },
})
export default class ContactAllListPane extends Vue {
    private items: ItemData[] = [];
    private list: NodeData[] = ListIndexData.userNodes;
    private indexList = ListIndexData.userIndexes;

    public mounted() {
        // no
    }

    private onOpenSend(data: ItemData) {
        // TODO
        const path = '/contact.info/' + data.key;
        RouterUtil.toByPath(path);
    }

    get getHeight(): number {
        const height = document.documentElement.clientHeight - 95;
        return height;
    }
}
</script>

<style lang="scss">

</style>
