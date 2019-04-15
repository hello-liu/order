package com.moss.server.dao;

import com.moss.server.model.SysUser;

import java.util.List;

public interface SysUserDao {

	SysUser login(String account);

	List<String> getUserPermis(Integer userId);

	int add(SysUser user);
	int del(Integer id);
	int update(SysUser user);
	int updateFlag(SysUser user);
	List<SysUser> list(SysUser user);

}