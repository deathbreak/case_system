package com.abc.case_system.controller;

import com.abc.case_system.service.CaseService;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class AdminCaseEvidenceCorController {

    @Autowired
    EvidenceService evidenceService;

    @Autowired
    CaseService caseService;

    // 处理关联
    @RequestMapping("/admin_processing_association")
    public String to_admin_processing_association(HashMap<String, Object> map) {
        map.put("countall",evidenceService.CountByCstatus(0));
        map.put("allconnect",evidenceService.GetConnectByCstatus(0));
//        map.put("alluser", evidenceService.GetUserByCstatus(0));
        return "admin/admin_processing_association";
    }

    // 历史关联维护
    @RequestMapping("/admin_maintain_for_history_association")
    public String to_admin_maintain_for_history_association() {
        return "admin/admin_maintain_for_history_association";
    }

    //
    @RequestMapping("/admin_connect_iframe")
    public String to_admin_connect_iframe(String cid, Integer eid, HashMap<String, Object> map){
        map.put("caseinfo", caseService.GetCaseByCaseId(cid));
        map.put("eviinfo", evidenceService.GetEviByEidversion(eid));
        return "admin/admin_connect_iframe";
    }
}
