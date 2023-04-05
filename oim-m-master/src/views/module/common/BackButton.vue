<template>
    <v-btn icon
           @click="back"
    >
        <v-icon>mdi-chevron-left</v-icon>
    </v-btn>
</template>

<script lang="ts">
    import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

    @Component({
        components: {},
    })
    export default class BackButton extends Vue {

        public back() {
            const length = window.history.length;
            const hasHistory = length > 1;
            hasHistory ? this.$router.go(-1) : this.$router.push('/');
        }
    }
</script>

<style lang="scss">

</style>
