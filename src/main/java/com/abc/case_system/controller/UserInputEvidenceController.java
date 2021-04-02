package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInputEvidenceController {

    // 新增证据
    @RequestMapping("/user_input_evidence")
    public String to_user_input_evidence() {
        return "user/user_input_evidence";
    }


}
