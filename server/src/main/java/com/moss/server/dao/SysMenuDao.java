package com.moss.server.dao;

import com.moss.server.model.SysMenu;

import java.util.HashMap;
import java.util.List;

public interface SysMenuDao {

	int add(SysMenu menu);
	int del(Integer id);
	int update(SysMenu menu);
	int updateFlag(SysMenu menu);
	List<SysMenu> list();
	List<SysMenu> listByUser(HashMap map);

}