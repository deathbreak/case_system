package com.abc.case_system.controller;

import com.abc.case_system.bean.ForBoolStr;
import com.abc.case_system.bean.Webdata;
import com.abc.case_system.bean.Webuser;
import com.abc.case_system.service.CaseService;
import com.abc.case_system.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserOtherInputEvidenceController {

    @Autowired
    WebService webService;

    @Autowired
    CaseService caseService;

    // 渠道证据录入
    @RequestMapping("/user_other_input_evidence")
    public String to_user_other_input_evidence(HashMap<String, Object> map) {
        List<Webdata> re = webService.GetAllEviInfo();
        map.put("info", re);
        map.put("msg", re.size());
        return "user/user_other_input_evidence";
    }

    @PostMapping("/user_other_submit_info")
    public String to_user_other_submit(Webdata webdata, HashMap<String, Object> map){
        map.put("caseinfo", caseService.GetAllCaseId());
        map.put("inputevi", webService.ReturnEviToInputPage(webdata));
        return "user/user_input_evidence";
    }

    @RequestMapping("/other_login")
    public String other_user_login(Webuser webuser, HttpSession session, HashMap<String, Object> map) {
        ForBoolStr check = webService.CheckWebLogin(webuser);
        if (check.getFlag()){
            session.setAttribute("other", webuser);
            return "redirect:/other_index";
        }else {
            map.put("msg", check.getMsg());
            return "other/other_login";
        }
    }

    @GetMapping("/other_index")
    public String other_user_index(HttpServletRequest request, HashMap<String, Object> map) {
        Webuser check_user = (Webuser) request.getSession().getAttribute("other");
        if (null == check_user){
            return "redirect:/other_login";
        }
        List<Webdata> re = webService.GetAllEviByUsername(check_user.getUsername());
        map.put("info", re);
        map.put("infocount", re.size());
        return "other/other_index";
    }

    @PostMapping("/other_index")
    public String post_other_user_index(Webdata webdata, HttpServletRequest request, HashMap<String, Object> map) {
        Webuser check_user = (Webuser) request.getSession().getAttribute("other");
        if (null == check_user){
            return "redirect:/other_login";
        }
        webdata.setSubmituser(check_user.getUsername());
        webService.AddNewUserEvi(webdata);
        List<Webdata> re = webService.GetAllEviByUsername(check_user.getUsername());
        map.put("info", re);
        map.put("infocount", re.size());
        return "other/other_index";
    }
}
