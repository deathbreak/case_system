package com.abc.case_system.controller;

import com.abc.case_system.bean.Connecttip;
import com.abc.case_system.service.CaseService;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class AdminCaseEvidenceCorController {

    @Autowired
    EvidenceService evidenceService;

    @Autowired
    CaseService caseService;

    // 处理关联
    @RequestMapping("/admin_processing_association")
    public String to_admin_processing_association(HashMap<String, Object> map) {
        map.put("countall", evidenceService.CountByCstatus(0));
        map.put("allconnect", evidenceService.GetConnectByCstatus(0));
        return "admin/admin_processing_association";
    }

    //iframe数据
    @RequestMapping("/admin_connect_iframe")
    public String to_admin_connect_iframe(String cid, Integer eid, HashMap<String, Object> map) {
        map.put("caseinfo", caseService.GetCaseByCaseId(cid));
        map.put("eviinfo", evidenceService.GetEviByEidversion(eid));
        return "admin/admin_connect_iframe";
    }

    //iframe数据
    @RequestMapping("/admin_connect_iframe2")
    public String to_admin_connect_iframe2(String cid, Integer eid, HashMap<String, Object> map) {
        map.put("caseinfo", caseService.GetCaseByCaseId(cid));
        map.put("eviinfo", evidenceService.GetEviByEidversion(eid));
        return "admin/admin_connect_iframe2";
    }

    //待处理证据确定关联
    @PostMapping("/admin_connect_true")
    public String to_admin_connect_true(String caseid, Integer eid) {
        evidenceService.UpdateEviConnect(eid, caseid, 1, "", "", "b", "a");
        return "redirect:/admin_processing_association";
    }

    //待处理证据拒绝关联
    @PostMapping("/admin_connect_false")
    public String to_admin_connect_false(String caseid, Integer eid, String msg) {
        evidenceService.UpdateEviConnect(eid, caseid, 2, msg, "", "b", "c");
        return "redirect:/admin_processing_association";
    }

    //历史维护证据拒绝关联
    @PostMapping("/admin_connect2_false")
    public String to_admin_connect2_false(String caseid, Integer eid, String msg) {
        if (evidenceService.IsExistEid(eid)) {
            evidenceService.UpdateEviConnect(eid, caseid, 2, msg, "", "a", "c");
        }
        return "redirect:/admin_processing_association";
    }

    @GetMapping("/admin_maintain_for_history_association")
    public String admin_maintain_for_history_association() {
        return "admin/admin_maintain_for_history_association";
    }

    // 历史关联维护
    @PostMapping("/admin_maintain_for_history_association")
    public String to_admin_maintain_for_history_association(String query, HashMap<String, Object> map) {
        List<Connecttip> con_query = evidenceService.GetConByQueryCaseid(query);
        if (null == con_query) {
            map.put("msg", 0);
            return "admin/admin_maintain_for_history_association";
        } else {
            map.put("msg", con_query.size());
            map.put("query_result", con_query);
            map.put("query", query);
            return "admin/admin_maintain_for_history_connect";
        }
    }


}
