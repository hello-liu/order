package com.moss.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.moss.common.model.BackModel;
import com.moss.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMaper {

	@Autowired
	SysUserService sysUserService;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	SysDeptService sysDeptService;

	@Autowired
	SysLogService sysLogService;

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysPermisService sysPermisService;

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

			//机构管理
			case "sysDept.add":return sysDeptService.add(json);
			case "sysDept.del":return sysDeptService.del(json);
			case "sysDept.update":return sysDeptService.update(json);
			case "sysDept.list":return sysDeptService.list(json);

			//log
			case "sysLog.list":return sysLogService.list(json);

			//角色管理
			case "sysRole.add":return sysRoleService.add(json);
			case "sysRole.del":return sysRoleService.del(json);
			case "sysRole.update":return sysRoleService.update(json);
			case "sysRole.list":return sysRoleService.list(json);


			case "sysPermis.getMenuAndFunc":return sysPermisService.getMenuAndFunc(json);
			case "sysPermis.save":return sysPermisService.save(json);
			case "sysPermis.getByOwner":return sysPermisService.getByOwner(json);


			default: return new BackModel("error","接口不存在");
		}

	}

}


