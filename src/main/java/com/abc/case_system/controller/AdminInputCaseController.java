package com.abc.case_system.controller;

import com.abc.case_system.bean.Casecheck;
import com.abc.case_system.bean.Systemcase;
import com.abc.case_system.service.CasecheckService;
import com.abc.case_system.service.SystemcaseService;
import com.abc.case_system.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class AdminInputCaseController {

    @Autowired
    SystemcaseService systemcaseService;

    @Autowired
    CasecheckService casecheckService;

    // 拉取系统案件
    @RequestMapping("/admin_get_system_case")
    public String to_admin_get_system_case(HashMap<String, Object> map) {
        map.put("systemcase", systemcaseService.GetAllSystemcase(0));
        map.put("systemcasecount", systemcaseService.GetIntAllSystemcase());
        return "admin/admin_get_system_case";
    }

    @PostMapping("/admin_submit_syscase_to_check")
    public String admin_submit_syscase_to_check(String case_id_submit) {
        systemcaseService.UpdateCaseCheckStatus(1, case_id_submit);
        return "redirect:/admin_get_system_case";
    }

    // update
    @PostMapping("/admin_update_case_info")
    public String admin_update_case_info(String flag, Systemcase systemcase) {
        casecheckService.UpdateCheckCase(flag, systemcase);
        return "redirect:/admin_check_case";
    }

    // delete
    @PostMapping("/admin_del_check_case")
    public String admin_del_check_case(String flag, String case_id) {
        casecheckService.DelCheckCase(flag, case_id);
        return "redirect:/admin_check_case";
    }

    // 手工新增案件
    @PostMapping("/admin_input_case_by_hand")
    public String to_admin_input_case_by_hand(Casecheck casecheck, HashMap<String, Object> map) {
        int casepeople_index = casecheck.getCasepeople().indexOf(",");
        String caseid = TimeInfo.time_to_string(casecheck.getCasetime()) + casecheck.getCasepeople().substring(casepeople_index + 1, casepeople_index + 19);
        casecheck.setCaseid(caseid);
        Boolean check = casecheckService.InputCaseService(casecheck);
        if (check) {
            return "redirect:/admin_check_case";
        } else {
            map.put("msg", "录入失败,系统中有相同的案件编号!");
            map.put("idcard", casecheckService.GetAllIdcard());
            map.put("inputcase", casecheck);
            return "admin/admin_input_case_by_hand";
        }
    }

    @GetMapping("/admin_input_case_by_hand")
    public String admin_input_case_by_hand(HashMap<String, Object> map) {
        map.put("idcard", casecheckService.GetAllIdcard());
        return "admin/admin_input_case_by_hand";
    }

    // 案件复核入库
    @RequestMapping("/admin_check_case")
    public String to_admin_check_case(HashMap<String, Object> map) {
        map.put("msgcount", casecheckService.GetIntCheckCase());
        map.put("syscasecheck", systemcaseService.GetAllSystemcase(1));
        map.put("inputcasecheck", casecheckService.GetAllCaseCheck());
        return "admin/admin_check_case";
    }

    // 退回审核
    @PostMapping("/admin_return_systemcase_check")
    public String admin_return_systemcase_check(String case_id) {
        systemcaseService.UpdateCaseCheckStatus(0, case_id);
        return "redirect:/admin_check_case";
    }

    // 审核入库   systemcase,casecheck ==>  case
    @PostMapping("/admin_checkcase_to_case")
    public String admin_checkcase_to_case(String flag, String case_id) {
        casecheckService.InputCase(flag, case_id);
        return "redirect:/admin_check_case";
    }


}
