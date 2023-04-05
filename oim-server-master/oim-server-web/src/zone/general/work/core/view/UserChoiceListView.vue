<template>
    <div class="app-container common-list-pane">
        <el-card>
            <div slot="header">
                <span>用户列表</span>
            </div>
            <div class="common-list-pane-search" style="margin-bottom: 20px">
                <el-row>
                    <el-col :span="20" style="float: left">
                        <el-form :inline="true" label-width="80px">
                            <el-form-item label="id">
                                <el-input v-model="query.id" placeholder="输入id"></el-input>
                            </el-form-item>
                            <el-form-item label="账号">
                                <el-input v-model="query.account" placeholder="输入账号"></el-input>
                            </el-form-item>
                        </el-form>
                        <el-form :inline="true" label-width="80px">
                            <el-form-item label="手机">
                                <el-input v-model="query.mobile" placeholder="输入手机"></el-input>
                            </el-form-item>
                            <el-form-item label="性别">
                                <el-radio-group v-model="query.gender">
                                    <el-radio :label="''">全部</el-radio>
                                    <el-radio :label="2">女</el-radio>
                                    <el-radio :label="1">男</el-radio>
                                    <el-radio :label="3">保密</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <el-col :span="4">
                        <div style="margin-left: 20px;margin-bottom: 10px;">
                            <el-button @click="queryList" type="primary">搜索</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div>
                <el-table
                    ref="table"
                    :data="list"
                    height="500"
                    border
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                    <el-table-column
                        type="selection"
                        width="55">
                    </el-table-column>
                    <el-table-column
                        prop="id"
                        label="id">
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
                        label="性别">
                        <template slot-scope="scope">
                            <span v-if="scope.row.gender===2">女</span>
                            <span v-if="scope.row.gender===1">男</span>
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
        <div slot="footer" class="edit-dialog-footer" style="margin-top: 15px">
            <el-button @click="cancel">取消</el-button>
            <el-button type="primary" @click="save">确认</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import Page from '@/app/com/common/data/Page';

import UserData from '@/zone/general/work/core/main/data/UserData';
import UserDataQuery from '@/zone/general/work/core/main/data/query/UserDataQuery';
import UserExtendClient from '@/zone/general/work/core/main/client/UserExtendClient';

@Component({
    components: {},
})
export default class UserChoiceListView extends Vue {
    private list: any[] = [];

    private page: Page = new Page();
    private query: UserDataQuery = new UserDataQuery();
    private items: UserData[] = [];

    @Prop({
        type: Boolean,
        required: false,
        default: () => true,
    })
    private single!: boolean;


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
        UserExtendClient.queryList(query, page, (result) => {
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

    public handleSelectionChange(selection: any) {
        if (this.single) {
            const viewName = 'table';
            const view: any = this.$refs[viewName];
            if (selection.length > 1) {
                view.clearSelection();
                view.toggleRowSelection(selection.pop());
            } else {
                this.items = selection;
            }
        } else {
            this.items = selection;
        }
    }

    public cancel() {
        this.onDone(false, []);
    }

    public save() {
        this.onDone(true, this.items);
    }

    @Emit('on-done')
    public onDone(done: boolean, items: UserData[]) {
    }
}
</script>

<style scoped>

</style>
