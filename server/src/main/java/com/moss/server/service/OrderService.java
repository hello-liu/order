package com.moss.server.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moss.common.model.BackModel;
import com.moss.common.model.CheckParamsModel;
import com.moss.common.model.PageModel;
import com.moss.server.common.Constant;
import com.moss.server.dao.OrderDao;
import com.moss.server.dao.SysMenuDao;
import com.moss.server.dao.SysUserDao;
import com.moss.server.model.Order;
import com.moss.server.model.SysMenu;
import com.moss.server.model.SysUser;
import com.moss.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;



	@Transactional
	public BackModel add(JSONObject json ) throws Exception{

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
		cps.add(new CheckParamsModel("deptId", 0,20,"[0-9]+" ) );
		cps.add(new CheckParamsModel("title", 0,50,"" ) );
		cps.add(new CheckParamsModel("content", 1,60,"" ) );

		BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

		Order order  = JSONObject.toJavaObject(json, Order.class);

		int result = orderDao.add(order);
		return new BackModel("ok","添加成功！");

	}

	@Transactional
	public BackModel del(JSONObject json ) {

		Integer id = json.getInteger("id");
		int result = orderDao.del(id);
		return new BackModel("ok","删除成功！");

	}

	@Transactional
	public BackModel update(JSONObject json ) {

		//验证请求参数
		List<CheckParamsModel> cps = new ArrayList<CheckParamsModel>();
        cps.add(new CheckParamsModel("deptId", 0,20,"[0-9]+" ) );
        cps.add(new CheckParamsModel("title", 0,50,"" ) );
        cps.add(new CheckParamsModel("content", 1,60,"" ) );

        BackModel backModel = Util.checkParams(json, cps);
		if(backModel != null){
			return backModel;
		}

        Order order = JSONObject.toJavaObject(json, Order.class);
		int result = orderDao.update(order);
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

		Order order  = JSONObject.toJavaObject(json, Order.class);
		int result = orderDao.updateFlag(order);
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

		//获取请求参数
		Integer pageNo = json.getInteger("pageNo");
		Integer pageNum = json.getInteger("pageNum");
		Order order = JSONObject.toJavaObject(json, Order.class);

		//分页查询
		PageHelper.startPage(pageNo, pageNum);
		List<Order> ms = orderDao.list(order);
		PageInfo page= new PageInfo<>(ms);

		return new BackModel("ok","查询成功！", new PageModel(page.getTotal(),ms) );

	}

}