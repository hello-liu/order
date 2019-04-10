
var util = {



  getUser:function(){
    var user = localStorage.getItem('user') ;
    var userData =JSON.parse(user);
    return userData;
  },

  getUserName:function(){
    // var user = localStorage.getItem('user') ;
    // var userData =JSON.parse(user);
    // return this.getUser().nickname;
    return 'admin';
  },

}
export default util
