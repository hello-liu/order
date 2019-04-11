
var util = {

  //保存本地信息
  saveInfo:function(key,value){
    localStorage.setItem(key,value);
  },
  //保存本地信息
  getInfo:function(key){
    return localStorage.getItem(key);
  },
  //登录成功后保存用户信息
  saveUserInfo:function(data){
    this.saveInfo('userFunc',JSON.stringify(data.userFunc) );
    this.saveInfo('userInfo',JSON.stringify(data.userInfo) );
    this.saveInfo('userMenu',JSON.stringify(data.userMenu) );
    this.saveInfo('userToken',data.userToken);
  },

  //获取本地用户信息
  getUser:function(){
    var user = this.getInfo("userInfo");
    var userData =JSON.parse(user);
    return userData;
  },

  //获取本地用户信息
  getMenu:function(){
    var menu = this.getInfo("userMenu");
    var menuData =JSON.parse(menu);
    return menuData;
  },

  //获取本地用户信息
  getFunc:function(){
    var func = this.getInfo("userFunc");
    var funcData =JSON.parse(func);
    return funcData;
  },

  //获取用户名
  getUserName:function(){
    return this.getUser().nickname;
    // return 'admin';
  },

  getTree:function(list){
    var roots = [];
    for(var i = 0; i < list.length; i++){
      var node = list[i];
      if(!node.pid){
        //无pid的根节点
        //检索他的子节点
        this.getChildren(node,list)
        roots.push(node);
      }else {

      }
    }
    return roots;
  },
  getChildren:function (root,list) {
    var children = [];
    for(var i = 0; i < list.length; i++){
      var node = list[i];
      if(root.id == node.pid){
        this.getChildren(node,list)
        children.push(node);
      }
    }
    if(children.length>0){
      root.children = children;
    }
  },
  //深拷贝对象
  copy:function (obj) {
    return JSON.parse(JSON.stringify(obj));
  },
  //通过子节点，获取所有的上层id
  getParent:function (id, tree) {
    var ids = []
    for(var i = 0; i < tree.length; i++){
      var node = tree[i]
      if(node.id == id){
        ids.push(id)
        return ids
      }else{
        //遍历孩子
        if(node.children){
          var temp = this.getChildrenParent(id,node.children);
          if(temp.length == 0) {
            //孩子中没有这个节点
          }else {
            //当前节点的id
            ids.push(node.id)
            //孩子的id
            ids = ids.concat(temp)
            return ids
          }
        }
      }
    }
    return ids
  },
  getChildrenParent:function(id,children) {
    var ids = []
    console.log(children.length)
    for(var i = 0; i < children.length; i++){
      var node = children[i]
      if(node.id == id){
        ids.push(id)
        return ids
      }else{
        //遍历孩子
        if(node.children){
          var temp = this.getChildrenParent(id,node.children);
          if(temp.length == 0) {
            //孩子中没有这个节点
          }else {
            //当前节点的id
            ids.push(node.id)
            //孩子的id
            ids = ids.concat(temp)
            return ids
          }
        }
      }
    }
    return ids;
  }


}
export default util
