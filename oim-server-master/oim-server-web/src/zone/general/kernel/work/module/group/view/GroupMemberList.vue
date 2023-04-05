<template>
    <div class="app-container common-list-pane">
        <el-card>
            <div slot="header">
                <span>列表</span>
            </div>
            <div class="common-list-pane-search" style="margin-bottom: 20px">
                <el-row>
                    <el-col :span="16" style="float: left">
                        <el-form ref="form" :inline="true" label-width="80px">
                            <el-form-item label="用户id">
                                <el-input v-model="query.userId" placeholder="输入id"></el-input>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <el-col :span="8">
                        <div style="margin-left: 20px;margin-bottom: 10px;">
                            <el-button @click="queryList" type="primary">搜索</el-button>
                            <el-button @click="openUserChoice" type="primary">添加成员</el-button>
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
                        prop="account"
                        label="帐号">
                    </el-table-column>
                    <el-table-column
                        prop="mobile"
                        label="手机">
                    </el-table-column>
                    <el-table-column
                        prop="nickname"
                        label="昵称">
                    </el-table-column>
                    <el-table-column
                        prop="userId"
                        label="群成员用户id">
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
                        fixed="right"
                        label="操作"
                        width="220">
                        <template slot-scope="scope">
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
            <GroupMemberEdit ref="editView" :data-id="editId" @on-done="editDone"></GroupMemberEdit>
        </el-dialog>
        <el-dialog :title="'选择用户'" :visible.sync="userChoiceVisible" width="800px" append-to-body>
            <UserChoiceListView ref="userChoiceListView"
                                :single="false"
                                @on-done="userChoiceDone"></UserChoiceListView>
        </el-dialog>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import Page from '@/app/com/common/data/Page';

import GroupMemberClient from '@/zone/general/kernel/work/module/group/main/client/GroupMemberClient';
import GroupMemberQuery from '@/zone/general/kernel/work/module/group/main/data/query/GroupMemberQuery';
import GroupMemberEdit from '@/zone/general/kernel/work/module/group/view/GroupMemberEdit.vue';
import UserChoiceListView from '@/zone/general/work/core/view/UserChoiceListView.vue';
import FieldUtil from '@/app/com/common/util/FieldUtil';
import UserDataExplain from '@/zone/general/work/core/main/explain/UserDataExplain';
import GroupMemberExtendClient from '@/zone/general/kernel/work/module/group/main/client/GroupMemberExtendClient';

@Component({
    components: {
        GroupMemberEdit,
        UserChoiceListView,
    },
})
export default class GroupMemberList extends Vue {
    private list: any[] = [];

    private page: Page = new Page();
    private query: GroupMemberQuery = new GroupMemberQuery();

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
        FieldUtil.setField(list, 'account', '', '');
        FieldUtil.setField(list, 'mobile', '', '');
        FieldUtil.setField(list, 'nickname', '', '');

        UserDataExplain.setExplain(list, 'userId', 'id', (data, explain) => {
            UserDataExplain.setExplainValue(data, explain, 'account', 'account');
            UserDataExplain.setExplainValue(data, explain, 'mobile', 'mobile');
            UserDataExplain.setExplainValue(data, explain, 'nickname', 'nickname');
        }, '');
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
        let groupId = this.groupId;
        if (!groupId) {
            groupId = '0';
        }
        query.groupId = groupId;
        GroupMemberClient.list(query, page, (result) => {
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
                GroupMemberClient.deleteById(id, (result) => {
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

    @Prop({
        type: String,
        required: false,
        default: () => '',
    })
    private groupId!: string;


    @Watch('groupId')
    public groupIdValue(id: string) {
        this.queryList();
    }

    private userChoiceVisible: boolean = false;

    public openUserChoice() {
        this.userChoiceVisible = true;
    }

    public userChoiceDone(choice: boolean, items: any[]) {
        this.userChoiceVisible = false;
        const own = this;
        if (items.length > 0) {
            const ids = [];
            for (const u of items) {
                ids.push(u.id);
            }
            own.save(ids);
        }
    }

    public save(userIds: any[]) {
        const own = this;
        const groupId = this.groupId;
        GroupMemberExtendClient.addByUserIds(groupId, userIds, (result) => {
            const info = result.info;
            if (info && info.success) {
                own.loadList();
            }
        });
    }
}
</script>

<style scoped>

</style>
