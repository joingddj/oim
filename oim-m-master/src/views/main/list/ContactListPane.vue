<template>
    <div class="contact-list-pane">
        <RootPane :items="model.nodes" :box="box"
                  @on-node-context-menu='onNodeContextMenu'
                  @on-item-selected="onItemSelected"
                  @on-item-context-menu='onItemContextMenu'>

        </RootPane>
    </div>
</template>

<script lang="ts">
    import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

    import RootPane from '@/views/common/list/RootPane.vue';
    import NodeData from '@/views/common/list/NodeData';
    import ItemBox from '@/views/common/list/ItemBox';
    import ItemData from '@/views/common/list/ItemData';
    import contactListPaneViewModel from '@/platform/vue/view/model/ContactListPaneViewModel';
    import RouterUtil from '@/common/vue/RouterUtil';

    @Component({
        components: {
            RootPane,
        },
    })
    export default class UserListPane extends Vue {
        private model = contactListPaneViewModel;
        private box: ItemBox = new ItemBox();

        public mounted() {
            // TODO
        }

        @Emit('on-node-context-menu')
        private onNodeContextMenu(e: MouseEvent, data: NodeData) {
            // 菜单
        }

        @Emit('on-item-selected')
        private onItemSelected(data: ItemData) {
            // TODO
            const path = '/contact.info/' + data.key;
            RouterUtil.toByPath(path);
        }

        @Emit('on-item-context-menu')
        private onItemContextMenu(e: MouseEvent, data: ItemData) {
            // 选中
        }
    }
</script>

<style scoped>

</style>
