package com.moss.server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moss.common.model.BackModel;
import com.moss.common.model.CheckParamsModel;
import com.moss.server.dao.SysPermisDao;
import com.moss.server.model.SysMenu;
import com.moss.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SysPermisService {

	@Autowired
	private SysPermisDao sysPermisDao;


	@Transactional
	public BackModel getMenuAndFunc(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		List<SysMenu> ms = sysPermisDao.getMenuAndFunc();
		PageInfo page= new PageInfo<SysMenu>(ms);

		return new BackModel("ok","查询成功！", ms );

	}

	@Transactional
	public BackModel save(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("flag", 0,50,"" ) );
		cps.add(new CheckParamsModel("ownerId", 1,60,"" ) );


		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		String flag = json.getString("flag");
		Integer ownerId = json.getInteger("ownerId");
		JSONArray menus = json.getJSONArray("menus");

		HashMap map = new HashMap();
		map.put("ownerId",ownerId);
		map.put("flag",flag);
		//删除以前的权限
		sysPermisDao.delByOwner(map);

		for(int i = 0; i<menus.size(); i++){
			Integer menu = menus.getInteger(i);
			//保存每个menu
			map.put("menuId",menu);
			sysPermisDao.add(map);

		}
		return new BackModel("ok","保存成功！" );

	}

	@Transactional
	public BackModel getByOwner(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("flag", 0,50,"" ) );
		cps.add(new CheckParamsModel("ownerId", 1,60,"" ) );


		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		String flag = json.getString("flag");
		Integer ownerId = json.getInteger("ownerId");

		HashMap map = new HashMap();
		map.put("flag",flag);
		map.put("ownerId",ownerId);

		List<Integer> ms = sysPermisDao.getByOwner(map);

		return new BackModel("ok","查询成功！", ms );

	}

}