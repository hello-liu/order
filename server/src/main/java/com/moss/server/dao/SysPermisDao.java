package com.moss.server.dao;

import com.moss.server.model.SysMenu;

import java.util.HashMap;
import java.util.List;

public interface SysPermisDao {

	List<SysMenu> getMenuAndFunc();
	int delByOwner(HashMap map);
	List<Integer> getByOwner(HashMap map);
	int add(HashMap map);

}