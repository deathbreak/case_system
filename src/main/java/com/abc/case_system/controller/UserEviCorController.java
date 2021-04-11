package com.abc.case_system.controller;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.ForRejectConnect;
import com.abc.case_system.bean.User;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserEviCorController {
    @Autowired
    EvidenceService evidenceService;


    // 待关联证据维护
    @RequestMapping("/user_pending_cor_maintain")
    public String to_user_pending_cor_maintain(String info, HashMap<String, Object> map, HttpServletRequest request) {
        if (null != info && !info.isEmpty()) {
            map.put("info", info);
        }
        User login_user = (User) request.getSession().getAttribute("login");
        map.put("msg", evidenceService.CountConnectBySubmitUser(login_user.getUsername()));
        map.put("eviinfo", evidenceService.GetAllUserPendingConnectEvidence(login_user.getUsername()));
        return "user/user_pending_cor_maintain";
    }

    // 修改
    @PostMapping("/user_update_pending_eviinfo")
    public String user_update_pending_eviinfo(Evidence evidence, RedirectAttributes attr) {
        attr.addAttribute("info", evidenceService.UpdatePendingEvi(evidence) ? null : "管理员已经关联了,本次修改失败,请去_证据维护_功能修改");
        return "redirect:/user_pending_cor_maintain";
    }

    // 退回关联处理
    @RequestMapping("/user_return_correlation")
    public String to_user_return_correlation(HashMap<String, Object> map, HttpServletRequest request) {
        User login_user = (User) request.getSession().getAttribute("login");
        List<ForRejectConnect> info = evidenceService.GetAllUserEvi(login_user.getUsername(), 2);
        map.put("info", info);
        map.put("infocount", info.size());
        return "user/user_return_correlation";
    }

    //退回关联修改并关联
    @PostMapping("/user_update_return_eviinfo")
    public String to_user_update_return_eviinfo(Evidence evidence, String caseid, String cunote){
        evidenceService.UpdateRejectEvi(evidence, caseid, cunote);
        return "redirect:/user_pending_cor_maintain";
    }

    // 申请解除关联
    @RequestMapping("/user_apply_for_disconnect")
    public String to_user_apply_for_disconnect() {
        return "user/user_apply_for_disconnect";
    }

    // 解除关联
    @PostMapping("/user_disconnect")
    public String to_user_disconnect(String caseid, Integer eid){
        evidenceService.DisConnect(eid, caseid);
        return "redirect:/user_return_correlation";
    }


}
