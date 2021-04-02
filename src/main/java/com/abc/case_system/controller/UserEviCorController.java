package com.abc.case_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserEviCorController {

    // 待关联证据维护
    @RequestMapping("/user_pending_cor_maintain")
    public String to_user_pending_cor_maintain(){
        return "user/user_pending_cor_maintain";
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
