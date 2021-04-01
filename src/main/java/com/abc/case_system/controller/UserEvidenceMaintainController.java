package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserEvidenceMaintainController {

    // 录入证据管理
    @RequestMapping("/user_maintain_for_input_evidence")
    public String to_user_maintain_for_input_evidence() {
        return "user/user_maintain_for_input_evidence";
    }
    // 退回维护处理
    @RequestMapping("/user_return_maintain_evidence")
    public String to_user_return_maintain_evidence() {
        return "user/user_return_maintain_evidence";
    }
}
