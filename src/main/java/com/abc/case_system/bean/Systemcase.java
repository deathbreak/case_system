package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Systemcase {         // 系统案件表:   (模拟的系统案件拉取)
    private String caseid;        // 案件编号  主键  以时间信息+第一个案件关联人的身份证号
                                  // 例2021年1月2日 3点45分56秒 + 421081199901012277
                                  //       ==>   20210102034556421081199901012277
    private String casetime;      // 有手工录入时间按手工的 没有就按提交时间与上面时间串一致
    private String casename;      // 案件名称/简称
    private String caseinfo;      // 案件内容
    private String casepeople;    // 案件关联的人   数据格式例 "张三,421081199901012222,,李四,421081199812121111,,"
    private Integer casestatus;   // 0/1/2 --- 侦查/结案/销案
    private String casenote;      // 案件审理侦查过程中的笔录和阶段处理结果
    private Integer casecheckstatus; // 0/1/2/3    未处理/在审核/已入库/被删除
}
