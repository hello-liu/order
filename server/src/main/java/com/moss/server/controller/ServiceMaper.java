package com.moss.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.moss.common.model.BackModel;
import com.moss.server.service.SysLogService;
import com.moss.server.service.SysMenuService;
import com.moss.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMaper {

	@Autowired
	SysUserService sysUserService;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	SysLogService sysLogService;

	public BackModel maper(JSONObject json ) throws Exception {
		String method = json.getString("method");
		switch(method){

			//user
			case "sysUser.login":return sysUserService.login(json);
			case "sysUser.logout":return sysUserService.logout(json);
			case "sysUser.add":return sysUserService.add(json);
			case "sysUser.del":return sysUserService.del(json);
			case "sysUser.update":return sysUserService.update(json);
			case "sysUser.updateFlag":return sysUserService.updateFlag(json);
			case "sysUser.list":return sysUserService.list(json);

			//menu
			case "sysMenu.add":return sysMenuService.add(json);
			case "sysMenu.del":return sysMenuService.del(json);
			case "sysMenu.update":return sysMenuService.update(json);
			case "sysMenu.list":return sysMenuService.list(json);
			case "sysMenu.updateFlag":return sysMenuService.updateFlag(json);

			//log
			case "log.list":return sysLogService.list(json);


			default: return new BackModel("error","接口不存在");
		}

	}

}


