package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.User;
import com.abc.case_system.dao.EvidenceMapper;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserEvidenceMaintainController {

    @Autowired
    EvidenceService evidenceService;

    @Autowired
    EvidenceMapper evidenceMapper;

    // 录入证据管理
    @RequestMapping("/user_maintain_for_input_evidence")
    public String to_user_maintain_for_input_evidence(HashMap<String, Object> map, HttpServletRequest request) {
        User login_user = (User) request.getSession().getAttribute("login");
        map.put("msg", evidenceService.CountConnectBySubmitUser(1, login_user.getUsername()));
        map.put("info", evidenceService.GetUserConByStatus(1, login_user.getUsername()));
        return "user/user_maintain_for_input_evidence";
    }


    // 检测是否有修改版本
    @ResponseBody
    @PostMapping("/user_check_more_eid")
    public int user_check_more_eid(String eid) {
        return evidenceService.CheckIsMoreEidVersion(eid);
    }

    // 取得修改版本证据
    @ResponseBody
    @PostMapping("/user_getevi_responsebody")
    public Evidence user_getevi_responsebody(String eid) {
        return evidenceService.GetEditEviByEid(eid);
    }

//    // 检测是否修改过  已废弃现使用前端检测
//    @ResponseBody
//    @PostMapping("/user_check_isedit")
//    public int user_check_isedit(int eidversion, String eurl, String enote) {
//        return evidenceService.IsEdit(eidversion, eurl, enote) ? 200 : 300;
//    }

    @PostMapping("/user_update_editevi")
    public String user_update_editevi(int flag, Evidence evidence) {
        evidenceService.UpdateEditEvi(flag, evidence);
        return "redirect:/user_maintain_for_input_evidence";
    }


    // 退回维护处理
    @RequestMapping("/user_return_maintain_evidence")
    public String to_user_return_maintain_evidence(HashMap<String, Object> map, HttpServletRequest request) {
        User login_user = (User) request.getSession().getAttribute("login");
        List<Evidence> re = evidenceService.GetRejectEviInfo(login_user.getUsername(), 2);
        map.put("msg", re.size());
        map.put("info", re);
        return "user/user_return_maintain_evidence";
    }

    @PostMapping("/user_update_eupdate")
    public String user_update_eupdate(int eid) {
        evidenceMapper.UpdateEviEupdateEtext(eid, 0, "");
        return "redirect:/user_maintain_for_input_evidence";
    }
}
