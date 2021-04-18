package com.abc.case_system.controller;

import com.abc.case_system.bean.Case;
import com.abc.case_system.bean.Forurl;
import com.abc.case_system.bean.User;
import com.abc.case_system.service.CaseService;
import com.abc.case_system.service.EvidenceService;
import com.abc.case_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    CaseService caseService;

    @Autowired
    EvidenceService evidenceService;

    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String _main(User user, String login_route, HttpSession session, HashMap<String, Object> map) {
        user.setRole(null == login_route ? 1 : 0);
        if (user.getUsername().equals("") && user.getPassword().equals("")) {
            map.put("msg", "用户名或密码不能为空");
            return "login";
        } else {
            switch (userService.QueryUserService(user, login_route)) {
                case "null_role":
                    map.put("msg", "登录状态有误,登录失败");
                    return "login";
                case "null_user":
                    map.put("msg", "该用户尚未注册,登录失败");
                    return "login";
                case "error_pwd":
                    map.put("msg", "用户名或密码错误");
                    return "login";
                case "admin_user":
                    session.setAttribute("login", user);
                    return "redirect:/admin_index";
                case "user":
                    session.setAttribute("login", user);
                    return "redirect:/user_index";
                default:
                    return "login";
            }
        }


    }


    @RequestMapping("/admin_index")
    public String to_admin_index() {
        return "admin/index";
    }

    @RequestMapping("/user_index")
    public String to_user_index() {
        return "user/index";
    }

    @PostMapping("/admin_get")
    public String admin_get(String query, HashMap<String, Object> map){
        List<Case> case_query = caseService.GetCaseByQuery(query);
        if (case_query == null){
            map.put("msg", 0);
            return "admin/admin_query";
        }else {
            map.put("msg", case_query.size());
            map.put("query_result", case_query);
            return "admin/admin_query";
        }
    }

    @PostMapping("/user_get")
    public String user_get(String query, HashMap<String, Object> map){
        List<Case> case_query = caseService.GetCaseByQuery(query);
        if (case_query == null){
            map.put("msg", 0);
            return "user/user_query";
        }else {
            map.put("msg", case_query.size());
            map.put("query_result", case_query);
            return "user/user_query";
        }
    }

    //evi_iframe数据
    @RequestMapping("/admin_evi_iframe")
    public String admin_evi_iframe(String connect, HashMap<String, Object> map) {
        List<Forurl> re = evidenceService.GetAllUrl(connect);
        map.put("info", re);
        map.put("msg", re.size());
        return "admin/admin_evi_iframe";
    }

    //evi_iframe数据
    @RequestMapping("/user_evi_iframe")
    public String user_evi_iframe(String connect, HashMap<String, Object> map) {
        List<Forurl> re = evidenceService.GetAllUrl(connect);
        map.put("info", re);
        map.put("msg", re.size());
        return "user/user_evi_iframe";
    }

}


