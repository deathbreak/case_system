package com.abc.case_system.service;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.ForBoolStr;
import com.abc.case_system.bean.Webdata;
import com.abc.case_system.bean.Webuser;
import com.abc.case_system.dao.WebdataMapper;
import com.abc.case_system.dao.WebuserMapper;
import com.abc.case_system.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {

    @Autowired
    WebdataMapper webdataMapper;

    @Autowired
    WebuserMapper webuserMapper;

    // 手机端用户登录验证
    public ForBoolStr CheckWebLogin(Webuser webuser) {
        if ("".equals(webuser.getUsername()) || null == webuser.getUsername()) {
            return new ForBoolStr(false, "用户名不能为空!!!");
        } else {
            if (webuserMapper.CountUsername(webuser.getUsername()) == 0) {
                return new ForBoolStr(false, "该用户名不存在!!!");
            } else {
                if (webuserMapper.GetPasswordByUsername(webuser.getUsername()).equals(webuser.getPassword())){
                    return new ForBoolStr(true, "");
                }else{
                    return new ForBoolStr(false, "密码不对!!!");
                }
            }
        }
    }


    //添加证据
    public void AddNewUserEvi(Webdata webdata){
        String nowtime = TimeInfo.get_now_time();
        webdata.setDatatime(nowtime);
        webdata.setCheckstat(nowtime + webdata.getSubmituser());
        webdataMapper.AddNewOne(webdata);
    }


    public List<Webdata> GetAllEviByUsername(String username){
        return webdataMapper.GetAllUserEvi(username);
    }

    public List<Webdata> GetAllEviInfo(){
        return webdataMapper.GetAllEvi();
    }

    public Evidence ReturnEviToInputPage(Webdata webdata){
        webdataMapper.UpdateStatus(webdata.getCheckstat());
        Evidence evidence = new Evidence();
        evidence.setEtime(webdata.getDatatime());
        evidence.setEurl(webdata.getUrl());
        evidence.setEnote(webdata.getNote());
        return evidence;
    }

}
