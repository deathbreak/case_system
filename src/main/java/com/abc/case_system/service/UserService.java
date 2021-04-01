package com.abc.case_system.service;

import com.abc.case_system.bean.User;
import com.abc.case_system.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public String QueryUserService(User user, String role){
        if(null != role && !role.equals("true")){
            return "null_role";
        }else{
            int user_check = userMapper.CountUserByUPR(user.getUsername(), user.getPassword(), user.getRole());
            if(user_check == 0){
                if(userMapper.CountUserByUR(user.getUsername(), user.getRole())==0){
                    return "null_user";
                }else{
                    return "error_pwd";
                }
            }else{
                if(null==role){
                    return "admin_user";
                }else{
                    return "user";
                }
            }
        }

    }
}
