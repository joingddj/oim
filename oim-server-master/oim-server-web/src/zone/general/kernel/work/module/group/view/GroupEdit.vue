<template>
    <div>
        <el-card>
            <div slot="header">
                <span>基本信息</span>
            </div>
            <div>
                <el-form ref="form" :model="data" :rules="ruleValidate"
                         :label-position="'right'"
                         label-width="120px">
                    <el-form-item label="群名称：" prop="name">
                        <el-input v-model="data.name" placeholder="群名称" type="text"/>
                    </el-form-item>
                    <el-form-item label="介绍：" prop="introduce">
                        <el-input v-model="data.introduce" placeholder="介绍" type="text"/>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
        <div slot="footer" class="edit-dialog-footer" style="margin-top: 15px">
            <el-button @click="cancel">取消</el-button>
            <el-button type="primary" :loading="saveLoading" @click="save">保存</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';

import BaseUtil from '@/app/lib/util/BaseUtil';
import GroupClient from '@/zone/general/kernel/work/module/group/main/client/GroupClient';
import Group from '@/zone/general/kernel/work/module/group/main/entity/Group';


@Component({
    components: {},
})
export default class GroupEdit extends Vue {
    private saveLoading: boolean = false;

    @Prop({
        type: String,
        required: false,
        default: () => '',
    })
    private dataId!: string;
    private data: Group = new Group();

    private ruleValidate = {};

    public mounted() {
        this.load();
    }

    public setData(data: Group) {
        this.data = data;
    }

    public cancel() {
        this.onDone(false);
    }

    public save(): void {
        const own = this;
        const data = this.data;
        (this.$refs as any).form.validate((valid: boolean) => {
            if (valid) {
                own.saveLoading = true;
                GroupClient.addOrUpdate(data, (result) => {
                    const info = result.info;
                    if (info && info.success) {
                        own.onDone(true);
                        own.setData(new Group());
                    }
                    own.saveLoading = false;
                });
            }
        });
    }

    public load(): void {
        const own = this;
        const id = own.dataId;
        if (BaseUtil.isNotEmpty(id)) {
            GroupClient.getById(id, (result) => {
                const body = result.body;
                if (body) {
                    own.setData(body);
                } else {
                    own.setData(new Group());
                }
            });
        } else {
            own.setData(new Group());
        }
    }

    @Watch('dataId')
    public dataIdValue(id: string) {
        this.load();
    }

    @Emit('on-done')
    public onDone(done: boolean) {
        // no
        const own = this;
        own.saveLoading = false;
    }
}
</script>

<style scoped>

</style>
