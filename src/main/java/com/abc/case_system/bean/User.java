package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@NoArgsConstructor：生成无参构造器；
//@RequiredArgsConstructor：生成包含final和@NonNull注解的成员变量的构造器；
//@AllArgsConstructor：生成全参构造器
//@Data：作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode @Getter @Setter @RequiredArgsConstructor
//@Builder：作用于类上，将类转变为建造者模式
//@Log：作用于类上，生成日志变量。针对不同的日志实现产品，有不同的注解：

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;      // 用户名
    private String password;      // 密码
    private Integer role;         // 角色   0/1    普通用户/管理员
}
