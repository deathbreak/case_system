package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminEvidenceManageController {


    // 证据信息维护
    @RequestMapping("/admin_maintain_for_evidence")
    public String to_admin_maintain_for_evidence() {
        return "admin/admin_maintain_for_evidence";
    }

    // 审批用户维护
    @RequestMapping("/admin_check_for_user_maintain")
    public String to_admin_check_for_user_maintain() {
        return "admin/admin_check_for_user_maintain";
    }
}
