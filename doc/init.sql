
-- 初始化管理员用户
insert into tb_sys_user(nickname, account, pwd, create_time, flag)
values ('admin', 'admin', '0S1Mf12lO02eHwZeOBOtdg==', now(), '0');

-- 初始化菜单
truncate table tb_sys_menu;
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(1,null,'el-icon-lx-home','dashboard','系统首页','0');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(2,null,'el-icon-lx-sort','sysMng','系统管理','0');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(3,2,'el-icon-lx-people','userMng','用户管理','0');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(4,2,'el-icon-lx-home','merchantMng','商户管理','0');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(5,2,'el-icon-lx-home','logMng','系统日志','0');


-- 初始化权限信息
truncate table tb_sys_func;
insert into tb_sys_func(name, value, flag)
values ('添加会员', 'user.add', '0');
insert into tb_sys_func(name, value, flag)
values ('删除会员', 'user.del', '0');
insert into tb_sys_func(name, value, flag)
values ('修改会员', 'user.update', '0');
insert into tb_sys_func(name, value, flag)
values ('修改会员flag', 'user.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('查询会员', 'user.list', '0');
insert into tb_sys_func(name, value, flag)
values ('获取会员', 'user.get', '0');
insert into tb_sys_func(name, value, flag)
values ('退出登录', 'user.logout', '0');

insert into tb_sys_func(name, value, flag)
values ('添加会员', 'member.add', '0');
insert into tb_sys_func(name, value, flag)
values ('删除会员', 'member.del', '0');
insert into tb_sys_func(name, value, flag)
values ('修改会员', 'member.update', '0');
insert into tb_sys_func(name, value, flag)
values ('修改会员flag', 'member.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('查询会员', 'member.list', '0');
insert into tb_sys_func(name, value, flag)
values ('获取会员', 'member.get', '0');

insert into tb_sys_func(name, value, flag)
values ('添加商户', 'merchant.add', '0');
insert into tb_sys_func(name, value, flag)
values ('删除商户', 'merchant.del', '0');
insert into tb_sys_func(name, value, flag)
values ('修改商户', 'merchant.update', '0');
insert into tb_sys_func(name, value, flag)
values ('修改商户flag', 'merchant.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('查询商户', 'merchant.list', '0');
insert into tb_sys_func(name, value, flag)
values ('获取商户', 'merchant.get', '0');

insert into tb_sys_func(name, value, flag)
values ('充值', 'busi.charge', '0');
insert into tb_sys_func(name, value, flag)
values ('消费', 'busi.consume', '0');
insert into tb_sys_func(name, value, flag)
values ('退款', 'busi.refund', '0');
insert into tb_sys_func(name, value, flag)
values ('积分兑换', 'busi.integralToMoney', '0');

insert into tb_sys_func(name, value, flag)
values ('会员流水', 'flow.list', '0');

insert into tb_sys_func(name, value, flag)
values ('系统日志', 'log.list', '0');