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
                    <el-form-item label="群id：" prop="groupId">
                        <el-input v-model="data.groupId" placeholder="群id" type="text"/>
                    </el-form-item>
                    <el-form-item label="备注名(群中显示昵称)：" prop="nickname">
                        <el-input v-model="data.nickname" placeholder="备注名(群中显示昵称)" type="text"/>
                    </el-form-item>
                    <el-form-item label="权限  1:群主 2:管理员 3:普通成员：" prop="position">
                        <el-input v-model="data.position" placeholder="权限  1:群主 2:管理员 3:普通成员" type="text"/>
                    </el-form-item>
                    <el-form-item label="群成员用户id：" prop="userId">
                        <el-input v-model="data.userId" placeholder="群成员用户id" type="text"/>
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
import GroupMemberClient from '@/zone/general/kernel/work/module/group/main/client/GroupMemberClient';
import GroupMember from '@/zone/general/kernel/work/module/group/main/entity/GroupMember';


@Component({
    components: {},
})
export default class GroupMemberEdit extends Vue {
    private saveLoading: boolean = false;

    @Prop({
        type: String,
        required: false,
        default: () => '',
    })
    private dataId!: string;
    private data: GroupMember = new GroupMember();

    private ruleValidate = {};

    public mounted() {
        this.load();
    }

    public setData(data: GroupMember) {
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
                GroupMemberClient.addOrUpdate(data, (result) => {
                    const info = result.info;
                    if (info && info.success) {
                        own.onDone(true);
                        own.setData(new GroupMember());
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
            GroupMemberClient.getById(id, (result) => {
                const body = result.body;
                if (body) {
                    own.setData(body);
                } else {
                    own.setData(new GroupMember());
                }
            });
        } else {
            own.setData(new GroupMember());
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
