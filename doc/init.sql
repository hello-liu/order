
-- 初始化管理员用户
insert into tb_sys_user(id,nickname, account, pwd, create_time, flag)
values (1,'admin', 'admin', '0S1Mf12lO02eHwZeOBOtdg==', now(), '0');
select nextval('tb_sys_user_id_seq');


-- 初始化菜单 39
truncate table tb_sys_menu;
insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(1,null,'el-icon-lx-home','dashboard','系统首页','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (35,1, '工单添加', 'order.add', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (36,1, '工单删除', 'order.del', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (37,1, '工单修改', 'order.update', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (38,1, '修改flag', 'order.updateFlag', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (39,1, '工单查询', 'order.list', '0', '1');
select nextval('tb_sys_menu_id_seq');


insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(2,null,'el-icon-lx-sort','sysMng','系统管理','0','0');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(3,2,'el-icon-lx-home','deptMng','机构管理','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (4,3, '机构添加', 'sysDept.add', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (5,3, '机构删除', 'sysDept.del', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (6,3, '机构修改', 'sysDept.update', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (7,3, '机构菜单flag', 'sysDept.updateFlag', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (8,3, '机构查询', 'sysDept.list', '0','1');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(9,2,'el-icon-lx-people','userMng','用户管理','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (10,9, '用户添加', 'sysUser.add', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (11,9, '用户删除', 'sysUser.del', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (12,9, '用户修改', 'sysUser.update', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (13,9, '修改用户flag', 'sysUser.updateFlag', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (14,9, '用户查询', 'sysUser.list', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (15,9, '用户获取', 'sysUser.get', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (16,9, '退出登录', 'sysUser.logout', '0', '1');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(17,2,'el-icon-lx-sort','menuMng','菜单管理','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (18,17, '菜单添加', 'sysMenu.add', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (19,17, '菜单删除', 'sysMenu.del', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (20,17, '菜单修改', 'sysMenu.update', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (21,17, '修改菜单flag', 'sysMenu.updateFlag', '0','1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (22,17, '菜单查询', 'sysMenu.list', '0','1');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(23,2,'el-icon-lx-profile','permisMng','权限管理','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (24,23, '获取所有菜单和方法', 'sysPermis.getMenuAndFunc', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (33,23, '保存权限', 'sysPermis.save', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (34,23, '获取权限', 'sysPermis.getByOwner', '0', '1');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(25,2,'el-icon-lx-home','logMng','系统日志','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (26,25, '系统日志', 'sysLog.list', '0', '1');
select nextval('tb_sys_menu_id_seq');

insert into tb_sys_menu(id,pid,icon,index,title,flag,type)
values(27,2,'el-icon-lx-group','roleMng','角色管理','0','0');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (28,27, '角色添加', 'sysRole.add', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (29,27, '角色删除', 'sysRole.del', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (30,27, '角色修改', 'sysRole.update', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (31,27, '角色菜单flag', 'sysRole.updateFlag', '0', '1');
select nextval('tb_sys_menu_id_seq');
insert into tb_sys_menu(id,pid,title,index,flag,type)
values (32,27, '角色查询', 'sysRole.list', '0', '1');
select nextval('tb_sys_menu_id_seq');


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
