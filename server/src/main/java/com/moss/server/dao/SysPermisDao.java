package com.moss.server.dao;

import com.moss.server.model.SysMenu;

import java.util.HashMap;
import java.util.List;

public interface SysPermisDao {

	List<SysMenu> getMenuAndFunc();
	int delByOwner(Integer ownerId);
	List<Integer> getByOwner(HashMap map);
	int add(HashMap map);

}