<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-copy"></i> 权限管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane label="角色权限" name="first">
                    <el-row :gutter="20">
                        <el-col :span="8">
                            <el-card shadow="hover" class="mgb20" >
                                <el-table :data="roleData" border stripe
                                          class="table"
                                          ref="roleTable"
                                          @row-click="onRole"
                                          highlight-current-row>
                                    <el-table-column type="index" width="55" align="center"></el-table-column>
                                    <el-table-column prop="name" label="角色名称" >
                                    </el-table-column>
                                </el-table>
                            </el-card>
                        </el-col>
                        <el-col :span="16">
                            <el-card shadow="hover">
                                <el-button type="primary" icon="el-icon-check" @click="save('role')">保存</el-button>
                                <el-tree
                                        :data="menuDate"
                                        ref="roleMenuTree"
                                        default-expand-all
                                        node-key="id"
                                        highlight-current
                                        show-checkbox
                                        :props="defaultProps"
                                        :leafOnly="true"
                                        :expand-on-click-node="false"
                                        :render-content="renderContent">
                                </el-tree>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="机构权限" name="second">
                    <el-row :gutter="20">
                        <el-col :span="8">
                            <el-card shadow="hover" class="mgb20" >
                                <el-tree
                                        :data="deptTree"
                                        ref="deptTree"
                                        default-expand-all
                                        node-key="id"
                                        highlight-current
                                        @node-click="onDeptTree"
                                        :expand-on-click-node="false"
                                        :props="defaultPropsDept">
                                </el-tree>
                            </el-card>
                        </el-col>
                        <el-col :span="16">
                            <el-card shadow="hover">
                                <el-button type="primary" icon="el-icon-check" @click="save('dept')">保存</el-button>
                                <el-tree
                                        :data="menuDate"
                                        ref="deptMenuTree"
                                        default-expand-all
                                        node-key="id"
                                        highlight-current
                                        show-checkbox
                                        :props="defaultProps"
                                        :expand-on-click-node="false"
                                        :leafOnly="true"
                                        :render-content="renderContent">
                                </el-tree>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="用户权限" name="third">
                    <el-row :gutter="20">
                        <el-col :span="8">
                            <el-card shadow="hover" class="mgb20" >
                                <div class="handle-box">
                                    <el-input v-model="select_word" placeholder="筛选关键词" class="handle-input mr10"></el-input>
                                    <el-button type="primary" icon="search" @click="searchUser">搜索</el-button>
                                </div>
                                <el-table :data="userData" border stripe
                                          class="table"
                                          ref="userTable"
                                          @row-click="onUser"
                                          highlight-current-row>
                                    <el-table-column prop="account" label="账号" >
                                    </el-table-column>
                                    <el-table-column prop="nickname" label="用户名" >
                                    </el-table-column>
                                </el-table>
                            </el-card>
                        </el-col>
                        <el-col :span="16">
                            <el-card shadow="hover">
                                <el-button type="primary" icon="el-icon-check" @click="save('user')">保存</el-button>
                                <el-tree
                                        :data="menuDate"
                                        ref="userMenuTree"
                                        default-expand-all
                                        node-key="id"
                                        highlight-current
                                        show-checkbox
                                        :props="defaultProps"
                                        :leafOnly="true"
                                        :expand-on-click-node="false"
                                        :render-content="renderContent">
                                </el-tree>
                            </el-card>
                        </el-col>
                    </el-row>
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
                selectedRole: '',
                selectedUser: '',
                select_word: '',
                message: 'first',
                roleData: [],
                userData: [],
                menuDate: [],
                cur_page: 1,
                pageSize:10,
                defaultProps: {
                    children: 'children',
                    label: 'title'
                },
                defaultPropsDept: {
                    children: 'children',
                    label: 'name'
                },
                deptTree: [],


            }
        },
        created() {
            this.getRole();
            this.getMenuTree();
            this.getDeptTree();
        },
        methods: {
            renderContent(h, { node, data, store }) {
                return (
                    <span class="custom-tree-node">
                    <span>{node.label}({data.type=='0'?'菜单':'功能'})</span>
                </span>);
            },

            // 获取数据
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
                        this.total = data.data.total;
                    }else{
                        //失败，进行提示
                    }
                })
            },
            getMenuTree(){
                this.$http.post('sysPermis.getMenuAndFunc', {}, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        let tree = this.$util.getTree(data.data)
                        this.menuDate = tree;
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            getDeptTree(){
                this.$http.post('sysDept.list', {}, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        let tree = this.$util.getTree(data.data)
                        this.deptTree = tree;
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            onRole(row, column, event){
                this.selectedRole =  row.id
                //获取角色的权限
                let params = {}
                params.flag = '3'
                params.ownerId = this.selectedRole
                this.$http.post('sysPermis.getByOwner', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        // let selectedKeys = [4,5,6,7,8]
                        let selectedKeys = data.data
                        this.$refs.roleMenuTree.setCheckedKeys(selectedKeys);
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })

            },
            onUser(row, column, event){
                this.selectedUser =  row.id
                //获取角色的权限
                let params = {}
                params.flag = '2'
                params.ownerId = this.selectedUser
                this.$http.post('sysPermis.getByOwner', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        // let selectedKeys = [4,5,6,7,8]
                        let selectedKeys = data.data
                        this.$refs.userMenuTree.setCheckedKeys(selectedKeys);
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })

            },
            save(flag){
                let params = {}
                if(flag == 'role' ){
                    params.flag = '3'//角色/权限
                    params.ownerId = this.selectedRole
                    let nodes = this.$refs.roleMenuTree.getCheckedNodes(false,true);
                    let menus = []
                    for(let i = 0; i<nodes.length; i++){
                        menus.push(nodes[i].id)
                    }
                    // let menus = this.$refs.roleMenuTree.getCheckedKeys(leafOnly, includeHalfChecked)
                    params.menus = menus
                }else if(flag == 'dept' ){
                    params.flag = '4'//机构/权限
                    // params.ownerId = this.selectedRole
                    params.ownerId = this.$refs.deptTree.getCurrentKey()
                    let nodes = this.$refs.deptMenuTree.getCheckedNodes(false,true);
                    let menus = []
                    for(let i = 0; i<nodes.length; i++){
                        menus.push(nodes[i].id)
                    }
                    params.menus = menus
                }else if(flag == 'user' ){
                    params.flag = '2'//用户/权限
                    params.ownerId = this.selectedUser
                    let nodes = this.$refs.userMenuTree.getCheckedNodes(false,true);
                    let menus = []
                    for(let i = 0; i<nodes.length; i++){
                        menus.push(nodes[i].id)
                    }
                    params.menus = menus
                }else{
                    return;
                }

                this.$http.post('sysPermis.save', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        this.$alert(data.msg, "成功！", {confirmButtonText: '确定',
                            callback: action => {
                                //刷新列表
                                // this.getData();
                            }
                        });
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })


            },
            onDeptTree(data){
                //选择树事件
                //获取角色的权限
                let params = {}
                params.flag = '4'
                let ownerId = data.id
                params.ownerId = ownerId
                this.$http.post('sysPermis.getByOwner', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        // let selectedKeys = [4,5,6,7,8]
                        let selectedKeys = data.data
                        this.$refs.deptMenuTree.setCheckedKeys(selectedKeys);
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            searchUser(){
                let params = {pageNo:this.cur_page,pageNum:this.pageSize};
                if(this.select_word){
                    params.nickname = this.select_word;
                    params.account = this.select_word;
                }else{
                    this.$alert("请填写查询条件！", "错误！", {confirmButtonText: '确定',});
                }
                this.$http.post('sysUser.list', params, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        this.userData = data.data.list;
                    }else{
                        //失败，进行提示
                    }
                })
            }

        },

        computed: {

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
</style>

