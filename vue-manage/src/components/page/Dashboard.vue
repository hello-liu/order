<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-copy"></i> 工单管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane label="我发起的工单" name="zero">
                    <div class="table">
                        <div class="container">
                            <div class="handle-box">
                                <el-button type="primary" icon="el-icon-plus" class="handle-del mr10" @click="add">添加</el-button>
                                <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                            </div>
                            <el-table :data="data" border stripe class="table" ref="multipleTable" >
                                <el-table-column type="selection" width="55" align="center"></el-table-column>
                                <el-table-column prop="title" label="标题" >
                                </el-table-column>
                                <el-table-column prop="deptName" label="接收部门" >
                                </el-table-column>
                                <el-table-column prop="roleName" label="接收角色" >
                                </el-table-column>
                                <el-table-column prop="userName" label="接收人" >
                                </el-table-column>
                                <el-table-column prop="flag" label="处理状态" :formatter="formatterFlag">
                                </el-table-column>
                                <el-table-column prop="createTime" label="创建时间" :formatter="formatter">
                                </el-table-column>
                                <el-table-column label="操作" width="120" align="center">
                                    <template slot-scope="scope">
                                        <el-button type="text" icon="el-icon-edit" :disabled=" scope.row.flag !='0' " @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                        <el-button type="text" icon="el-icon-lx-top" @click="handleSend(scope.$index, scope.row)">发送</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <div class="pagination">
                                <el-pagination background @current-change="handleCurrentChange" :page-size="pageSize"
                                               layout="prev, pager, next" :total="total">
                                </el-pagination>
                            </div>
                        </div>

                        <!-- 编辑弹出框 -->
                        <el-dialog title="编辑工单" :visible.sync="editVisible" width="50%">
                            <el-form ref="form" :model="form" label-width="80px">
                                <el-form-item label="id" :hidden="true">
                                    <el-input v-model="form.id" ></el-input>
                                </el-form-item>
                                <el-form-item label="*接收部门" 	>
                                    <el-cascader :options="deptData"
                                                 v-model="form.deptId"
                                                 :props="defaultProps"
                                                 style="width: 100%"
                                                 :show-all-levels="true"
                                                 @change="changeDept"
                                                 change-on-select>
                                    </el-cascader >
                                </el-form-item>
                                <el-form-item label="*接收角色">
                                    <el-select v-model="form.roleId" placeholder="请选择">
                                        <el-option
                                                v-for="item in roleData"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="*接收人">
                                    <el-select v-model="form.userId" placeholder="请选择">
                                        <el-option
                                                v-for="item in userData"
                                                :key="item.id"
                                                :label="item.nickname"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="*标题" 	>
                                    <el-input v-model="form.title" ></el-input>
                                </el-form-item>
                                <el-form-item label="内容" 	>
                                    <el-input type='textarea' :rows="5" v-model="form.content" ></el-input>
                                </el-form-item>
                                <el-form-item label="发起人" 	>
                                    <el-input v-model="form.userName" :disabled="true"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="editVisible = false">取 消</el-button>
                                <el-button type="primary" @click="saveEdit">确 定</el-button>
                            </span>
                        </el-dialog>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="待处理工单" name="first">
                    <el-table :data="tableData2" border stripe class="table" ref="multipleTable" >
                        <el-table-column type="selection" width="55" align="center"></el-table-column>
                        <el-table-column prop="title" label="标题" >
                        </el-table-column>
                        <el-table-column prop="deptName" label="接收部门" >
                        </el-table-column>
                        <el-table-column prop="roleName" label="接收角色" >
                        </el-table-column>
                        <el-table-column prop="userName" label="接收人" >
                        </el-table-column>
                        <el-table-column prop="flag" label="处理状态" :formatter="formatterFlag">
                        </el-table-column>
                        <el-table-column prop="createTime" label="创建时间" :formatter="formatter">
                        </el-table-column>
                        <el-table-column label="操作" width="150" align="center">
                            <template slot-scope="scope">
                                <el-button type="text" icon="el-icon-edit" :disabled=" scope.row.flag !='1' " @click="handleEdit(scope.$index, scope.row)">转发</el-button>
                                <el-button type="text" icon="el-icon-lx-top" @click="handleDeal(scope.$index, scope.row)">处理完成</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="pagination">
                        <el-pagination background @current-change="handleCurrentChange" :page-size="pageSize"
                                       layout="prev, pager, next" :total="total">
                        </el-pagination>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="已经处理工单" name="second">
                    <el-table :data="tableData3" border stripe class="table" ref="multipleTable" >
                        <el-table-column type="selection" width="55" align="center"></el-table-column>
                        <el-table-column prop="title" label="标题" >
                        </el-table-column>
                        <el-table-column prop="deptName" label="接收部门" >
                        </el-table-column>
                        <el-table-column prop="roleName" label="接收角色" >
                        </el-table-column>
                        <el-table-column prop="userName" label="接收人" >
                        </el-table-column>
                        <el-table-column prop="flag" label="处理状态" :formatter="formatterFlag">
                        </el-table-column>
                        <el-table-column prop="createTime" label="创建时间" :formatter="formatter">
                        </el-table-column>

                    </el-table>
                    <div class="pagination">
                        <el-pagination background @current-change="handleCurrentChange" :page-size="pageSize"
                                       layout="prev, pager, next" :total="total">
                        </el-pagination>
                    </div>
                </el-tab-pane>

            </el-tabs>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'tabs',
        data() {
            return {
                message: 'zero',
                tableData: [],
                tableData2: [],
                tableData3: [],
                cur_page: 1,
                pageSize:10,
                total:0,
                select_word: '',
                editVisible: false,
                isEdit:false,
                deptData: [],
                roleData: [],
                userData: [],
                form: {
                    id: '',
                    deptId: [],
                    roleId: '',
                    userId: '',
                    title: '',
                    content: '',
                    userName : 'admin',
                    flag: '0',
                },
                formEmpty: {
                    id: '',
                    deptId: [],
                    roleId: '',
                    userId: '',
                    title: '',
                    content: '',
                    userName : 'admin',
                    flag: '0',
                },
                defaultProps: {
                    children: 'children',
                    label: 'name',
                    value: 'id',
                },


            }
        },
        created() {
            this.getData();
            this.getDeptData();
            this.getRole();
            this.getData2();
            this.getData3();
        },
        methods: {


            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取数据
            getData() {
                let params = {pageNo: this.cur_page, pageNum: this.pageSize};
                if (this.select_word) {
                    params.title = this.select_word;
                }
                params.createUserId = this.$util.getUserId();
                this.$http.post('order.list', params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //成功
                        this.tableData = data.data.list;
                        this.total = data.data.total;
                    } else {
                        //失败，进行提示
                    }
                })

            },
            // 获取数据
            getData2() {
                let params = {pageNo: this.cur_page, pageNum: this.pageSize};

                params.userId = this.$util.getUserId();
                params.flag = '1';
                this.$http.post('order.list', params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //成功
                        this.tableData2 = data.data.list;
                        this.total = data.data.total;
                    } else {
                        //失败，进行提示
                    }
                })

            },
            // 获取数据
            getData3() {
                let params = {pageNo: this.cur_page, pageNum: this.pageSize};

                params.userId = this.$util.getUserId();
                params.flag = '2';
                this.$http.post('order.list', params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //成功
                        this.tableData3 = data.data.list;
                        this.total = data.data.total;
                    } else {
                        //失败，进行提示
                    }
                })

            },
            search() {
                this.getData();
            },
            formatter(row, column) {
                return ("" + row.createTime).substring(0, 10);
            },
            formatterFlag(row, column) {
                if (row.flag == '0') {
                    return '未发送'
                } else if (row.flag == '1'){
                    return '已发送'
                } else if (row.flag == '2'){
                    return '已处理'
                }else{
                    return ""
                }
            },
            handleEdit(index, row) {
                this.isEdit = true;
                //深拷贝
                let temp = JSON.parse(JSON.stringify(row));
                temp.deptId = this.$util.getParent(temp.deptId,this.deptData);
                this.form = temp;
                this.editVisible = true;
            },
            handleSend(index, row) {
                var params = {id:row.id , flag : "1"};
                var method = "order.updateFlag";
                this.$http.post(method, params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //添加成功
                        this.$alert(data.msg, "成功！", {
                            confirmButtonText: '确定',
                            callback: action => {

                                this.editVisible = false;
                                //刷新列表
                                this.getData();
                            }
                        });
                    } else {
                        //失败，进行提示
                        this.$alert(data.msg + data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            handleDeal(index, row) {
                var params = {id:row.id , flag : "2"};
                var method = "order.updateFlag";
                this.$http.post(method, params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //添加成功
                        this.$alert(data.msg, "成功！", {
                            confirmButtonText: '确定',
                            callback: action => {

                                this.editVisible = false;
                                //刷新列表
                                this.getData2();
                            }
                        });
                    } else {
                        //失败，进行提示
                        this.$alert(data.msg + data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            // 保存编辑
            saveEdit() {
                let method = 'order.add'
                if (this.form.id) {
                    method = 'order.update'
                }
                let params = this.$util.copy(this.form);
                if(params.deptId instanceof Array){
                    params.deptId = params.deptId[params.deptId.length-1]
                }

                params.createUserId = this.$util.getUserId();

                this.$http.post(method, params, this).then(respose => {
                    let data = respose.data
                    if ("ok" == data.code) {
                        //添加成功
                        this.$alert(data.msg, "成功！", {
                            confirmButtonText: '确定',
                            callback: action => {

                                this.editVisible = false;
                                //刷新列表
                                this.getData();
                            }
                        });
                    } else {
                        //失败，进行提示
                        this.$alert(data.msg + data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })


            },
            //新增
            add() {
                this.form = JSON.parse(JSON.stringify(this.formEmpty))
                this.isEdit = false;
                this.editVisible = true;
            },
            getDeptData(){

                this.$http.post('sysDept.list', {}, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        let tree = this.$util.getTree(data.data)
                        this.deptData = tree;
                    }else{
                        //失败，进行提示
                        // this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            changeDept(values){
                //通过角色和部门获取用户
                let params = {pageNo:this.cur_page,pageNum:this.pageSize};

                params.deptId = values[values.length-1];

                this.$http.post('sysUser.list', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        this.userData = data.data.list;
                    }else{
                        //失败，进行提示
                    }
                })

            },
            getRole() {
                let params = {pageNo:this.cur_page,pageNum:this.pageSize};
                if(this.select_word){
                    params.name = this.select_word;
                }
                this.$http.post('sysRole.list', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        this.roleData = data.data.list;
                    }else{
                        //失败，进行提示
                    }
                })
            },



        },



        computed: {
            data() {
                return this.tableData;
            },
            getUserName(){
                this.$util.getUserName();
            },
        },
    }

</script>

<style>
    .message-title{
        cursor: pointer;
    }
    .handle-row{
        margin-top: 30px;
    }
    .el-row {
        margin-bottom: 20px;
    }
    .mgb20 {
        margin-bottom: 20px;
    }
    .table{
        width: 100%;
        font-size: 14px;
    }

    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
    .mr10{
        margin-right: 10px;
    }
</style>

