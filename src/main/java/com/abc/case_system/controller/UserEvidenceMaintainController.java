package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.User;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class UserEvidenceMaintainController {

    @Autowired
    EvidenceService evidenceService;

    // 录入证据管理
    @RequestMapping("/user_maintain_for_input_evidence")
    public String to_user_maintain_for_input_evidence(HashMap<String, Object> map, HttpServletRequest request) {
        User login_user = (User) request.getSession().getAttribute("login");
        map.put("msg", evidenceService.CountConnectBySubmitUser(1, login_user.getUsername()));
        map.put("info", evidenceService.GetUserConByStatus(1, login_user.getUsername()));
        return "user/user_maintain_for_input_evidence";
    }

    @ResponseBody
    @PostMapping("/user_check_more_eid")
    public int user_check_more_eid(String eid){
        return evidenceService.IsMoreEidVersion(eid)? 200:300;
    }

    @ResponseBody
    @PostMapping("/user_getevi_responsebody")
    public Evidence user_getevi_responsebody(String eid){
        return evidenceService.GetEditEviByEid(eid);
    }

    // 退回维护处理
    @RequestMapping("/user_return_maintain_evidence")
    public String to_user_return_maintain_evidence() {
        return "user/user_return_maintain_evidence";
    }
}
