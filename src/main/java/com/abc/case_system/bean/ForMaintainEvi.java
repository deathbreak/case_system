package com.abc.case_system.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForMaintainEvi {

    private Integer cstatus;   // 0/1/2/3/4    未处理/已处理/申请拒绝/申请解除关联/解除关联
    private String cuser;      // 提交关联的用户
    private String caseid;     // 要关联的案件编号
    private Integer eid;       // 要进行关联的证据eidversion最新版本
    private String canote;     // 管理员留言
    private String cunote;     // 用户留言
    private String ceid;       // 对应evidence中的eid   主键
    private Integer estatus;   // 0/1/2   修改/使用中/拒绝
    private Integer newid;     // 修改中的版本id
}
