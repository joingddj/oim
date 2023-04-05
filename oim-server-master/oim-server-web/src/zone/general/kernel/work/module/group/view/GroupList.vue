<template>
    <div class="app-container common-list-pane">
        <el-card>
            <div slot="header">
                <span>列表</span>
            </div>
            <div class="common-list-pane-search" style="margin-bottom: 20px">
                <el-row>
                    <el-col :span="20" style="float: left">
                        <el-form ref="form" :inline="true" label-width="80px">
                            <el-form-item label="名称">
                                <el-input placeholder="输入名称"></el-input>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <el-col :span="4">
                        <div style="margin-left: 20px;margin-bottom: 10px;">
                            <el-button @click="queryList" type="primary">搜索</el-button>
                            <el-button @click="handleEdit" type="primary">新增</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div>
                <el-table
                    :data="list"
                    height="500"
                    border
                    style="width: 100%">
                    <el-table-column
                        type="index"
                        width="50">
                    </el-table-column>
                    <el-table-column
                        prop="name"
                        label="群名称">
                    </el-table-column>
                    <el-table-column
                        prop="number"
                        label="群号码">
                    </el-table-column>
                    <el-table-column
                        prop="introduce"
                        label="介绍">
                    </el-table-column>
                    <el-table-column
                        prop="updatedDateTime"
                        label="更新时间">
                    </el-table-column>
                    <el-table-column
                        prop="createdDateTime"
                        label="创建时间">
                    </el-table-column>
                    <el-table-column
                        label="群成员">
                        <template slot-scope="scope">
                            <el-button @click="showMemberList(scope.row.id)" type="text" size="mini">查看成员</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="220">
                        <template slot-scope="scope">
                            <el-button @click="handleEdit(scope.row)" type="text" size="mini">编辑</el-button>
                            <el-button @click="handleDelete(scope.row)" type="text" size="mini" style="color: #fa4359">
                                删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="common-list-pane-pagination">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="page.number"
                    :page-sizes="[100, 200, 300, 400]"
                    :page-size="page.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="page.totalCount">
                </el-pagination>
            </div>
        </el-card>

        <el-dialog :title="editTitle" :visible.sync="editVisible" width="800px">
            <GroupEdit ref="editView" :data-id="editId" @on-done="editDone"></GroupEdit>
        </el-dialog>

        <el-dialog :title="memberListTitle" :visible.sync="memberListVisible" width="1000px">
            <GroupMemberList ref="groupMemberListView"
                             :group-id="groupId"
            ></GroupMemberList>
        </el-dialog>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import Page from '@/app/com/common/data/Page';

import GroupClient from '@/zone/general/kernel/work/module/group/main/client/GroupClient';
import GroupQuery from '@/zone/general/kernel/work/module/group/main/data/query/GroupQuery';
import GroupEdit from '@/zone/general/kernel/work/module/group/view/GroupEdit.vue';
import GroupMemberList from '@/zone/general/kernel/work/module/group/view/GroupMemberList.vue';

@Component({
    components: {
        GroupEdit,
        GroupMemberList,
    },
})
export default class GroupList extends Vue {
    private list: any[] = [];

    private page: Page = new Page();
    private query: GroupQuery = new GroupQuery();

    private editVisible: boolean = false;
    private editId: string = '';
    private editTitle: string = '';


    public mounted() {
        this.loadList();
    }

    public handleSizeChange(size: number) {
        this.page.size = size;
        this.loadList();
    }

    public handleCurrentChange(pageNumber: number) {
        this.page.number = pageNumber;
        this.loadList();
    }

    public setList(list: any[]) {
        this.list = list;
    }

    public setPage(page: Page) {
        if (page) {
            this.page = page;
        }
    }

    public queryList() {
        const own = this;
        const page = own.page;
        page.number = 1;

        this.loadList();
    }

    public loadList() {
        const own = this;
        const page = own.page;
        const query = own.query;
        GroupClient.list(query, page, (result) => {
            const info = result.info;
            const body = result.body;
            if (info && info.success && body) {
                const list: any[] = body.items;
                const p: Page = body.page;
                own.setPage(p);
                own.setList(list);
            }
        });
    }

    public handleEdit(data: any) {
        const id = (data) ? data.id : '';
        this.editId = id;
        this.editTitle = (id) ? '编辑' : '新增';
        this.showEdit();
    }

    public handleDelete(data: any) {
        if (data) {
            const id = (data) ? data.id : '';
            const own = this;
            this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(() => {
                GroupClient.deleteById(id, (result) => {
                    const info = result.info;
                    if (info && info.success) {
                        own.loadList();
                    }
                });
            }).catch(() => {
                // no
            });
        }
    }

    public showEdit() {
        this.editVisible = true;
    }

    public hiddenEdit() {
        this.editVisible = false;
        this.editId = '';
    }

    public editDone(done: boolean) {
        this.hiddenEdit();
        this.loadList();
    }

    private memberListVisible: boolean = false;
    private groupId: string = '';
    private memberListTitle: string = '群成员';

    public showMemberList(groupId: string) {
        this.groupId = groupId;
        this.memberListVisible = true;
    }
}
</script>

<style scoped>

</style>
