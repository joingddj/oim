<template>
    <div v-show="show" class="oim-load-mark">
        <div class="oim-load-box">
            <div v-if="icon" class="oim-loading" style="left: 28%;top: 15%;">
                <v-icon style="font-size: 50px;color: #fff;">{{icon}}</v-icon>
            </div>
            <div v-else class="oim-loading">
                <v-progress-circular
                        :size="36"
                        :width="1.5"
                        color="#fff"
                        indeterminate
                        style="position: absolute;left: -18px;top: -18px;"
                ></v-progress-circular>
            </div>
            <div class="oim-load-content"> {{tips}}</div>
        </div>
    </div>
</template>


<script lang="ts">
    import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

    @Component({
        components: {},
    })
    export default class Loading extends Vue {
        @Prop({
            type: Boolean,
            required: false,
            default: () => (false),
        })
        private show: boolean = false;

        @Prop({
            type: String,
            required: false,
            default: () => (''),
        })
        private icon!: string;

        @Prop({
            type: String,
            required: false,
            default: () => (''),
        })
        private tips!: string;

        public isShow() {
            return this.show;
        }

        public open() {
            this.show = true;
        }

        public close() {
            this.show = false;
        }
    }
</script>

<style lang="scss">
    .oim-load-mark {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: 9999;
    }

    .oim-load-box {
        position: fixed;
        z-index: 3;
        width: 7.6em;
        min-height: 7.6em;
        top: 180px;
        left: 50%;
        margin-left: -3.8em;
        background: rgba(0, 0, 0, 0.7);
        text-align: center;
        border-radius: 5px;
        color: #ffffff;
    }

    .oim-load-content {
        margin-top: 64%;
        font-size: 14px;
    }

    .oim-loading {
        position: absolute;
        width: 0;
        left: 50%;
        top: 38%;
    }
</style>
