package com.moss.server.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moss.common.model.BackModel;
import com.moss.common.model.CheckParamsModel;
import com.moss.common.model.PageModel;
import com.moss.server.dao.SysMenuDao;
import com.moss.server.model.SysMenu;
import com.moss.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	@Transactional
	public BackModel add(JSONObject json ) throws Exception{


		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("name", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("icon", 0,50,"" ) );
		cps.add(new CheckParamsModel("index", 1,60,"" ) );
		cps.add(new CheckParamsModel("title", 1,20,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		SysMenu menu = JSONObject.toJavaObject(json, SysMenu.class);
		int result = sysMenuDao.add(menu);
		return new BackModel("ok","添加成功！");

	}

	@Transactional
	public BackModel del(JSONObject json ) {

		Integer id = json.getInteger("id");
		int result = sysMenuDao.del(id);
		return new BackModel("ok","删除成功！");

	}

	@Transactional
	public BackModel update(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("id", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("nickname", 0,50,"" ) );
		cps.add(new CheckParamsModel("account", 1,60,"" ) );
		cps.add(new CheckParamsModel("phone", 1,20,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		SysMenu member = JSONObject.toJavaObject(json, SysMenu.class);
		int result = sysMenuDao.update(member);
		return new BackModel("ok","修改成功！");

	}

	@Transactional
	public BackModel updateFlag(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("id", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("flag", 0,8,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		SysMenu menu = JSONObject.toJavaObject(json, SysMenu.class);
		int result = sysMenuDao.updateFlag(menu);
		return new BackModel("ok","修改成功！");

	}

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

		List<SysMenu> ms = sysMenuDao.list();
		PageInfo page= new PageInfo<>(ms);

		return new BackModel("ok","查询成功！", new PageModel(page.getTotal(),ms) );

	}

}