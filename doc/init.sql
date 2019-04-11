
-- 初始化管理员用户
insert into tb_sys_user(nickname, account, pwd, create_time, flag)
values ('admin', 'admin', '0S1Mf12lO02eHwZeOBOtdg==', now(), '0');

-- 初始化菜单
truncate table tb_sys_menu;
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(1,null,'el-icon-lx-home','dashboard','系统首页','0');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(2,null,'el-icon-lx-sort','sysMng','系统管理','0');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(3,2,'el-icon-lx-people','userMng','用户管理','0');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(4,2,'el-icon-lx-home','merchantMng','商户管理','0');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_menu(id,pid,icon,index,title,flag)
values(5,2,'el-icon-lx-home','logMng','系统日志','0');
select nextval('tb_sys_dept_id_seq');


-- 初始化权限信息
truncate table tb_sys_func;
insert into tb_sys_func(name, value, flag)
values ('用户添加', 'sysUser.add', '0');
insert into tb_sys_func(name, value, flag)
values ('用户删除', 'sysUser.del', '0');
insert into tb_sys_func(name, value, flag)
values ('用户修改', 'sysUser.update', '0');
insert into tb_sys_func(name, value, flag)
values ('修改用户flag', 'sysUser.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('用户查询', 'sysUser.list', '0');
insert into tb_sys_func(name, value, flag)
values ('用户获取', 'sysUser.get', '0');
insert into tb_sys_func(name, value, flag)
values ('退出登录', 'sysUser.logout', '0');

insert into tb_sys_func(name, value, flag)
values ('系统日志', 'sysLog.list', '0');

insert into tb_sys_func(name, value, flag)
values ('菜单添加', 'sysMenu.add', '0');
insert into tb_sys_func(name, value, flag)
values ('菜单删除', 'sysMenu.del', '0');
insert into tb_sys_func(name, value, flag)
values ('菜单修改', 'sysMenu.update', '0');
insert into tb_sys_func(name, value, flag)
values ('修改菜单flag', 'sysMenu.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('菜单查询', 'sysMenu.list', '0');


insert into tb_sys_func(name, value, flag)
values ('机构添加', 'sysDept.add', '0');
insert into tb_sys_func(name, value, flag)
values ('机构删除', 'sysDept.del', '0');
insert into tb_sys_func(name, value, flag)
values ('机构修改', 'sysDept.update', '0');
insert into tb_sys_func(name, value, flag)
values ('机构菜单flag', 'sysDept.updateFlag', '0');
insert into tb_sys_func(name, value, flag)
values ('机构查询', 'sysDept.list', '0');


-- 初始化机构
insert into tb_sys_dept(id, pid, code, name, flag, create_time, remark)
values (1,null,'001','米牛','0',now(),'');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_dept(id, pid, code, name, flag, create_time, remark)
values (2,1,'00101','米牛小作坊1','0',now(),'');
select nextval('tb_sys_dept_id_seq');
insert into tb_sys_dept(id, pid, code, name, flag, create_time, remark)
values (3,1,'00102','米牛小作坊2','0',now(),'');
select nextval('tb_sys_dept_id_seq');
