package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.service.CaseService;
import com.abc.case_system.service.EvidenceService;
import com.abc.case_system.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class UserInputEvidenceController {

    @Autowired
    CaseService caseService;

    @Autowired
    EvidenceService evidenceService;

    // 新增证据
    @GetMapping("/user_input_evidence")
    public String to_user_input_evidence(HashMap<String, Object> map) {
        map.put("caseinfo", caseService.GetAllCaseId());
        return "user/user_input_evidence";
    }

    @PostMapping("/user_input_evidence")
    public String user_input_evidence(Evidence evidence, HashMap<String, Object> map) {
        if (evidenceService.IsInputEviExist(evidence)){
            map.put("caseinfo", caseService.GetAllCaseId());
            map.put("inputevi", evidence);
            map.put("msg", "这个证据已经添加过了");
            return "user/user_input_evidence";
        }else{
            evidenceService.InsertNewEvidence(evidence);
            return "redirect:/user_pending_cor_maintain";
        }
    }


}
