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
    // 退回关联处理
    @RequestMapping("/user_return_correlation")
    public String to_user_return_correlation() {
        return "user/user_return_correlation";
    }
    // 申请证据关联
    @RequestMapping("/user_apply_evidence_correlation")
    public String to_user_apply_evidence_correlation() {
        return "user/user_apply_evidence_correlation";
    }

}