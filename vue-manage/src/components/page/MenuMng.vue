<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" >
                    <el-tree
                            :data="treeDate"
                            ref="tree"
                            default-expand-all
                            node-key="id"
                            highlight-current
                            :props="defaultProps"
                            @node-click="onTree">
                    </el-tree>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-card shadow="hover">
                    <el-form ref="form" :model="form" label-width="100px">
                        <el-form-item label="id" :hidden="true">
                            <el-input v-model="form.id"></el-input>
                        </el-form-item>
                        <el-form-item label="上级" :hidden="true">
                            <el-input v-model="form.pid" ></el-input>
                        </el-form-item>
                        <el-form-item label="上级">
                            <el-input v-model="form.ptitle" :disabled="true"></el-input>
                        </el-form-item>
                        <el-form-item label="*编号">
                            <el-input v-model="form.index"></el-input>
                        </el-form-item>
                        <el-form-item label="*名称">
                            <el-input v-model="form.title" ></el-input>
                        </el-form-item>
                        <el-form-item label="*图标">
                            <el-input v-model="form.icon" ></el-input>
                        </el-form-item>
                        <el-form-item label="*状态">
                            <el-select v-model="form.flag" placeholder="请选择">
                                <el-option label="正常" value="0"></el-option>
                                <el-option label="停用" value="1"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="">
                            <el-button type="primary" icon="el-icon-check" @click="save">保存</el-button>
                            <el-button type="success" icon="el-icon-plus" @click="addChild">添加下级</el-button>
                        </el-form-item>

                    </el-form>
                </el-card>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        name: 'dashboard',
        data() {
            return {
                form:{
                  id:'',
                  pid:'',
                  ptitle:'',
                  icon:'',
                  index:'',
                  title:'',
                  flag:'0',
                },
                formEmpty:{
                    id:'',
                    pid:'',
                    ptitle:'',
                    icon:'',
                    index:'',
                    title:'',
                    flag:'0',
                },
                defaultProps: {
                    children: 'children',
                    label: 'title'
                },
                treeDate: [],

            }
        },
        components: {

        },
        created(){
            this.getData();
        },
        methods: {
            getData(){
                this.$http.post('sysMenu.list', {}, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        //构建机构树
                        let tree = this.$util.getTree(data.data)
                        console.log(data);
                        this.treeDate = tree;
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            onTree(data){
                //选择树事件
                this.form = this.$util.copy(data)
                // this.form.
                // this.form.
            },
            save(){
                let method = 'sysMenu.add'
                if(this.form.id){
                    method = 'sysMenu.update'
                }
                if(this.form.id){

                }
                this.$http.post(method, this.form, this).then(respose => {
                    let data = respose.data
                    if("ok" == data.code){
                        //成功
                        this.$alert(data.msg, "成功！", {confirmButtonText: '确定',
                            callback: action => {
                                //刷新列表
                                this.getData();
                            }
                        });
                    }else{
                        //失败，进行提示
                        this.$alert(data.msg+data.data, "错误！", {confirmButtonText: '确定',});
                    }
                })
            },
            addChild(){
                this.form =  this.$util.copy(this.formEmpty)
                let node = this.$refs.tree.getCurrentNode();
                this.form.pid = node.id
                this.form.ptitle = node.title

            }

        }
    }

</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }
    .mgb20 {
        margin-bottom: 20px;
    }
</style>
