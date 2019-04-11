package com.moss.server.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moss.common.model.BackModel;
import com.moss.common.model.CheckParamsModel;
import com.moss.common.model.PageModel;
import com.moss.server.dao.SysDeptDao;
import com.moss.server.model.SysDept;
import com.moss.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;

	@Transactional
	public BackModel add(JSONObject json ) throws Exception{


		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("code", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("name", 0,50,"" ) );
		cps.add(new CheckParamsModel("flag", 0,8,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		SysDept dept = JSONObject.toJavaObject(json, SysDept.class);
		int result = sysDeptDao.add(dept);
		return new BackModel("ok","添加成功！");

	}

	@Transactional
	public BackModel del(JSONObject json ) {

		Integer id = json.getInteger("id");
		int result = sysDeptDao.del(id);
		return new BackModel("ok","删除成功！");

	}

	@Transactional
	public BackModel update(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("code", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("name", 0,50,"" ) );
		cps.add(new CheckParamsModel("name", 0,8,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		SysDept dept = JSONObject.toJavaObject(json, SysDept.class);
		int result = sysDeptDao.update(dept);
		return new BackModel("ok","修改成功！");

	}

	@Transactional
	public BackModel list(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		List<SysDept> ms = sysDeptDao.list();

		return new BackModel("ok","查询成功！", ms );

	}

}