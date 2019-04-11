-- 启动postgres
-- pg_ctl -D /usr/local/var/postgres -l /usr/local/var/postgres/server.log start
-- 创建角色和用户
-- createuser order -P
-- createdb order -O order



-- 角色表
drop table if exists tb_sys_role;
create table tb_sys_role(
  id		serial,
  name		varchar(50),
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 方法表
drop table if exists tb_sys_func;
create table tb_sys_func(
  id		serial,
  pid int,
  menu_id int,
  name		varchar(50),
  value		varchar(50),
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 菜单
drop table if exists tb_sys_menu;
create table tb_sys_menu(
  id		serial,
  pid int,
  icon		varchar(50),
  index		varchar(50),
  title		varchar(50),
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 用户表
drop table if exists tb_sys_user;
create table tb_sys_user(
  id		serial,
  dept_id int,
  nickname		varchar(50),
  sex		varchar (10),
  age		int,
  pwd		varchar (60),
  account		varchar (60) unique ,
  phone varchar(20),
  email varchar(50),
  idnumber varchar(20),
  address varchar(255),
  money		int,-- 余额
  integral		int,-- 积分
  head		int,
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 权限配置表
drop table if exists tb_sys_permis;
create table tb_sys_permis(
  id		serial,
  owner_id		int , -- 用户、角色
  permis_id		int , -- 角色、权限
  flag		varchar (8), --[1] 1-用户/角色 2-用户/权限 3-角色/权限 4-机构/权限
  type		varchar (8), --[1] 1-菜单 2-功能
  create_time		timestamp,
  remark		varchar (255)
);

-- 部门表
drop table if exists tb_sys_dept;
create table tb_sys_dept(
  id		serial,
  pid		int,
  code		varchar (8),
  name		varchar (50),
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 参数表
drop table if exists tb_sys_param;
create table tb_sys_param(
  id		serial,
  type		varchar (20),
  code		varchar (20),
  name		varchar (80),
  flag		varchar (8),
  create_time		timestamp,
  remark		varchar (255)
);

-- 日志表
drop table if exists tb_sys_log;
create table tb_sys_log(
  id	serial,
  token	varchar(50) ,
  user_ip	varchar(50),
  json	text,
  result text ,
  use_time int,
  method varchar (50),
  flag	varchar (8),
  create_time	timestamp,
  remark	varchar (255)
);

-- 附件信息表
drop table if exists tb_sys_file;
create table tb_sys_file(
  id	serial,
  size	int,
  name	varchar (100),
  class	varchar (20),
  img_type	varchar (20),
  flag	varchar (8),
  create_time	timestamp,
  user_id	int,
  remark	varchar(255)
);

