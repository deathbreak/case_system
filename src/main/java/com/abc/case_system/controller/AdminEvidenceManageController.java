package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 检测是否有修改版本
    @ResponseBody
    @PostMapping("/admin_check_more_eid")
    public int admin_check_more_eid(String eid) {
        return evidenceService.IsMoreEidVersion(eid) ? 200 : 300;
    }

    // 取得修改以及原版本证据
    @ResponseBody
    @PostMapping("/admin_getevi_responsebody")
    public Evidence admin_getevi_responsebody(int flag, String info) {
        return evidenceService.GetAdminEditEvi(flag, info);
    }

    @PostMapping("/admin_editevi_true")   //确认
    public String admin_editevi_true(String old_id, String new_id){
        System.out.println(old_id + ",,," +new_id);
        return "redirect:/admin_check_for_user_maintain";
    }

    @PostMapping("/admin_editevi_false")   //拒绝
    public String admin_editevi_false(String old_id, String new_id, String msg){
        System.out.println(old_id + ",,," +new_id + ",,," + msg);
        return "redirect:/admin_check_for_user_maintain";
    }

    // 证据信息维护
    @RequestMapping("/admin_maintain_for_evidence")
    public String to_admin_maintain_for_evidence() {
        return "admin/admin_maintain_for_evidence";
    }




}
