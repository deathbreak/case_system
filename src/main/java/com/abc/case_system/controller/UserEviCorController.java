package com.abc.case_system.controller;

import com.abc.case_system.bean.Case;
import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.User;
import com.abc.case_system.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class UserEviCorController {
    @Autowired
    EvidenceService evidenceService;


    // 待关联证据维护
    @RequestMapping("/user_pending_cor_maintain")
    public String to_user_pending_cor_maintain(String info, HashMap<String, Object> map, HttpServletRequest request) {
        if(null != info && !info.isEmpty()){
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
    public String to_user_return_correlation() {
        return "user/user_return_correlation";
    }

    // 申请解除关联
    @RequestMapping("/user_apply_for_disconnect")
    public String to_user_apply_for_disconnect() {
        return "user/user_apply_for_disconnect";
    }


}
