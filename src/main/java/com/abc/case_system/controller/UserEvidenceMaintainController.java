package com.abc.case_system.controller;

import com.abc.case_system.bean.User;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // 退回维护处理
    @RequestMapping("/user_return_maintain_evidence")
    public String to_user_return_maintain_evidence() {
        return "user/user_return_maintain_evidence";
    }
}
