package com.moss.server.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moss.common.model.BackModel;
import com.moss.common.model.CheckParamsModel;
import com.moss.common.model.PageModel;
import com.moss.server.dao.SysLogDao;
import com.moss.server.model.SysLog;
import com.moss.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	@Transactional
	public BackModel list(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("pageNo", 0,99,"[0-9]+" ) );
		cps.add(new CheckParamsModel("pageNum", 0,99,"[0-9]+" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		//获取请求参数
		Integer pageNo = json.getInteger("pageNo");
		Integer pageNum = json.getInteger("pageNum");
		SysLog log = JSONObject.toJavaObject(json, SysLog.class);

		//分页查询
		PageHelper.startPage(pageNo, pageNum);
		List<SysLog> ms = sysLogDao.list(log);
		PageInfo page= new PageInfo<>(ms);

		return new BackModel("ok","查询成功！", new PageModel(page.getTotal(),ms) );

	}

}