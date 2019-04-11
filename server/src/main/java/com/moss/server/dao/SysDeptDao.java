package com.moss.server.dao;


import com.moss.server.model.SysDept;

import java.util.HashMap;
import java.util.List;

public interface SysDeptDao {

	int add(SysDept dept);
	int del(Integer id);
	int update(SysDept dept);
	List<SysDept> list();
	List<SysDept> listByUser(HashMap map);

}