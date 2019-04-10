package com.moss.server.controller;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.moss.common.model.BackModel;
import com.moss.server.dao.SysLogDao;
import com.moss.server.model.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    ServiceMaper serviceMaper;

    @Autowired
    Permissions permissions;

    @Autowired
    SysLogDao sysLogDao;

    @RequestMapping(value = "/api")
    public BackModel api(@RequestBody String message,
                         @RequestHeader(value="token",required=false) String token,
                         HttpServletRequest request) {


        String userIp = request.getRemoteAddr();
        String method = "";
        BackModel result = null;
        long start = System.currentTimeMillis();

        try{
            JSONObject json = JSONObject.parseObject(message);

            method = json.getString("method");

            if( StringUtils.isEmpty(token) ){
                token = json.getString("token");
            }
            json.put("token",token);

            BackModel p = permissions.validation(json);
            if( p != null){
                result = p;
            }else {
                result = serviceMaper.maper(json);

            }
        }catch (JSONException e){
            e.printStackTrace();
            result = new BackModel("error","参数错误！","json format error");
        }catch (Exception e){
            e.printStackTrace();
            result = new BackModel("error","系统内部处理异常！","");
        }finally {

            SysLog log = new SysLog();
            log.setToken(token);
            log.setUserIp(userIp);
            log.setJson(message);
            BackModel resultLog = new BackModel(result.getCode(),result.getMsg());
            log.setResult(JSONObject.toJSONString(resultLog));
            int useTime = (int) (System.currentTimeMillis() - start);
            log.setUseTime(useTime);
            log.setMethod(method);
            //保存日志
            sysLogDao.add(log);
        }
        return result;

    }


}
