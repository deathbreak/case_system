package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evidence {           // 证据表
    private Integer eidversion;   // 并发环境中自增主键也能保证提交id版本的唯一性
    private String eid;           // 案件编号+第一次生成的eidversion
    private String etime;         // 证据录入时间
    private String elasttime;     // 最后一次修改时间
    private String ecaseid;       // 关联的案件id
    private String eurl;          // url,,,,url,,,,url   证据内容  url格式是存放的   图片/语音/视频/文档  的url地址
    private String ehistory;      // 证据是重要的东西，不允许删除，只允许修改，即使修改了也要保存历史的记录
                                  // 数据格式  update_eid,new_eid   新的往后面累加
    private String enote;         // 备注
    private String euser;         // 操作用户信息
    private Integer eupdate;      // 0/1/2   被修改了没被管理员确定/使用中的证据版本/被管理员拒绝
    private Integer estatus;      // 证据状态   0/1    失效/有效
}
