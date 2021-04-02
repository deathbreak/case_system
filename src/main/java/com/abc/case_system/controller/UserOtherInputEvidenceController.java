package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserOtherInputEvidenceController {

    // 渠道证据录入
    @RequestMapping("/user_other_input_evidence")
    public String to_user_other_input_evidence() {
        return "user/user_other_input_evidence";
    }

}
