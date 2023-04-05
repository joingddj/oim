<template>
    <div class="face-pane" unselectable="on" onmousedown="return false;">
        <v-tabs
            v-model="tab"
            height="25"
        >
            <template v-for="data of tabs">
                <v-tab
                    :key="data.key"
                >
                    {{ data.name }}
                </v-tab>
            </template>
        </v-tabs>
        <div class="face-list-pane">
            <v-tabs-items v-model="tab">
                <template v-for="item of items">
                    <v-tab-item
                        v-if="item.visible"
                        :key="item.id"
                    >
                        <div class="face">
                            <template v-for="face of item.faces">

                                <a v-if="face.visible" :title="face.text" @click="onFace(face)"
                                   :style="getWarpSize(face)">
                                    <img :src="face.path" :title="face.text" :style="getImageSize(face)"
                                         alt="face"/>
                                </a>
                            </template>
                        </div>
                    </v-tab-item>
                </template>
            </v-tabs-items>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import app from '@/app/App';
import FaceBox from '@/app/com/main/module/support/face/box/FaceBox';
import FaceCategory from '@/app/com/main/module/support/face/data/FaceCategory';
import FaceItem from '@/app/com/main/module/support/face/data/FaceItem';

@Component({
    components: {},
})
export default class FacePane extends Vue {

    private tab = '';
    private tabs: Array<{ name: string, key: string }> = [];
    private items: FaceCategory[] = [];

    public created() {
        this.initializeEvent();
    }

    public mounted() {
        this.initializeData();
    }

    private initializeData() {
        const own = this;
        const faceBox: FaceBox = app.appContext.getMaterial(FaceBox);
        this.items = faceBox.getFaceCategories();
        const items = this.items;
        const tabs: Array<{ name: string, key: string }> = [];
        for (const v of items) {
            if (v.visible) {
                tabs.push({name: v.name, key: v.id});
            }
        }
        this.tabs = tabs;
        if (tabs.length > 0 && this.tab === '') {
            this.tab = tabs[0].key;
        }
    }

    private initializeEvent() {
        const own = this;
    }

    private onFace(value: FaceItem) {
        this.selected(value);
    }

    private onTab(tab: string) {
        this.tab = tab;
    }

    private getImageSize(face: FaceItem) {
        const height = face.height;
        const width = face.width;
        if (height && width) {
            return {
                height: height + 'px',
                width: width + 'px',
            };
        } else {
            return {};
        }
    }

    private getWarpSize(face: FaceItem) {
        let height = face.height;
        let width = face.width;
        if (height && width) {
            height = height + 4;
            width = width + 4;
            return {
                height: height + 'px',
                width: width + 'px',
            };
        } else {
            return {};
        }
    }

    @Emit('on-selected')
    private selected(value: FaceItem) {
        // 选中
    }
}
</script>

<style scoped>
.face-pane {

}

.face-middle {
    position: absolute;
    height: 220px;
    width: 100%;
}

.face-bottom {
    position: absolute;
    bottom: 0;
    right: 0;
    left: 0;
}

.face-pane-warp {
    position: relative;
    height: 210px;
    overflow-y: auto;
}

.face-list-pane {
    height: 180px;
    overflow-y: auto;
    /*margin: 15px 10px;*/
    /*padding-right: 20px;*/
}

.face {
    margin: 10px;
}

.face a {
    float: left;
    /*width: 28px;*/
    /*height: 28px;*/
    font-size: 18px;

    border-bottom: 1px solid #f0f0f0;
    border-right: 1px solid #f0f0f0;
    cursor: pointer;
    text-align: center;
    /*padding-top: 2px;*/
    padding: 2px;
}

.face img {
    /*width: 24px;*/
    /*height: 24px;*/
    /*max-height: 50%;*/
    max-width: 100%;
}


.expression {
    z-index: 20;
}

</style>
