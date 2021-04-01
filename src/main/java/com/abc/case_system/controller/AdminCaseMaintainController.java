package com.abc.case_system.controller;

import com.abc.case_system.bean.Case;
import com.abc.case_system.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
public class AdminCaseMaintainController {

    @Autowired
    CaseService caseService;

    // 入库案件维护
    @RequestMapping("/admin_maintain_for_case")
    public String to_admin_maintain_for_case() {
        return "admin/admin_maintain_for_case";
    }

    // 查询需要维护的案件
    @RequestMapping("/admin_query_maintain_case")
    public String admin_query_maintain_case(String query, HashMap<String, Object> map){
        List<Case> case_query = caseService.GetCaseByQuery(query);
        if (case_query == null){
            map.put("msg", 0);
            map.put("query", query);
            return "admin/admin_maintain_query";
        }else {
            map.put("msg", case_query.size());
            map.put("query_result", case_query);
            map.put("query", query);
            return "admin/admin_maintain_query";
        }
    }

    // 修改
    @PostMapping("/admin_update_case_maintain")
    public String admin_update_case_maintain(String query, Case _case, RedirectAttributes attr){
        caseService.UpdateCase(_case);
        attr.addAttribute("query", query);
        return "redirect:/admin_query_maintain_case";
    }



}
