package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCaseEvidenceCorController {


    // 处理关联
    @RequestMapping("/admin_processing_association")
    public String to_admin_processing_association() {
        return "admin/admin_processing_association";
    }

    // 历史关联维护
    @RequestMapping("/admin_maintain_for_history_association")
    public String to_admin_maintain_for_history_association() {
        return "admin/admin_maintain_for_history_association";
    }
}
