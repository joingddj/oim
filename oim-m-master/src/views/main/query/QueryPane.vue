<template>
    <div class="query-root-pane">
        <div>
            <v-app-bar
                    color="primary"
                    dark
            >
                <back-button></back-button>
                <v-toolbar-title>查找</v-toolbar-title>
                <v-spacer></v-spacer>
            </v-app-bar>
            <v-tabs
                    v-model="tab"
                    centered
                    icons-and-text
                    height="40"
            >
                <v-tabs-slider></v-tabs-slider>
                <v-tab href="#tab-1">
                    <v-icon>mdi-account</v-icon>
                </v-tab>
                <v-tab href="#tab-2">
                    <v-icon>group</v-icon>
                </v-tab>
            </v-tabs>
        </div>
        <div class="query-root-b">
            <v-tabs-items v-model="tab">
                <v-tab-item
                        :value="'tab-1'"
                >
                    <user-query-pane></user-query-pane>
                </v-tab-item>
                <v-tab-item
                        :value="'tab-2'"
                >
                    <group-query-pane></group-query-pane>
                </v-tab-item>
            </v-tabs-items>
        </div>


    </div>

</template>

<script lang="ts">
    import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
    import BackButton from '@/views/module/common/BackButton.vue';
    import UserQueryPane from '@/views/module/user/find/UserFindPane.vue';
    import GroupQueryPane from '@/views/module/group/find/GroupFindPane.vue';

    @Component({
        components: {
            BackButton,
            UserQueryPane,
            GroupQueryPane,
        },
    })
    export default class QueryPane extends Vue {
        private tab: string = '';
    }
</script>

<style lang="scss">
    .query-root-pane {
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
    .query-root-b{
        width: 100%;
        top: 50px;
        bottom: 0;
        /*overflow-y: auto;*/
        overflow: hidden;
        position: absolute;

        .v-window {
            height: 100%;
        }

        .v-window__container {
            height: 100%;
        }

        .v-window-item {
            height: 100%;
        }
    }
</style>
