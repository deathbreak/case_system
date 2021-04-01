package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManageEvidenceController {

    // 录入证据查询
    @RequestMapping("/user_query_evidence")
    public String to_user_query_evidence() {
        return "user/user_query_evidence";
    }
    // 证据维护关联
    @RequestMapping("/user_maintain_for_correlation")
    public String to_user_maintain_for_correlation() {
        return "user/user_maintain_for_correlation";
    }
}
