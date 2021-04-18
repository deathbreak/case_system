package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class AdminEvidenceManageController {

    @Autowired
    EvidenceService evidenceService;

    // 审批用户维护
    @RequestMapping("/admin_check_for_user_maintain")
    public String to_admin_check_for_user_maintain(HashMap<String, Object> map) {
        List<Evidence> re = evidenceService.GetEditEviByEupdateStat(0, 1);
        map.put("info", re);
        map.put("msg", re.size());
        return "admin/admin_check_for_user_maintain";
    }


    // 证据信息维护
    @RequestMapping("/admin_maintain_for_evidence")
    public String to_admin_maintain_for_evidence() {
        return "admin/admin_maintain_for_evidence";
    }




}
