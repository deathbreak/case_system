package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Idcard {
    private String name;        // 身份证姓名
    private String number;      // 身份证号码      主键
    private Integer sex;        // 性别  0/1  0-女  1-男  (身份证号码的第十七位判断性别,如果为奇数则为男性,偶数则为女性)
    private String birthday;    // 出生日期   数据类型类似19951010
    private String home;        // 住址
    private String imageurl;    // 头像在服务器存放地址url
}
