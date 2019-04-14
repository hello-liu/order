package com.moss.server.dao;

import com.moss.server.model.SysRole;

import java.util.HashMap;
import java.util.List;

public interface SysRoleDao {

	int add(SysRole role);
	int del(Integer id);
	int update(SysRole role);
	List<SysRole> list(SysRole role);
	List<SysRole> listByUser(HashMap map);

}