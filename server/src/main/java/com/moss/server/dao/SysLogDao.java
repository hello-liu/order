package com.moss.server.dao;

import com.moss.server.model.SysLog;

import java.util.List;

public interface SysLogDao {

	int add(SysLog log);
	List<SysLog> list(SysLog log);



}