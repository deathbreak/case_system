package com.abc.case_system.dao;

import com.abc.case_system.bean.Systemcase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemcaseMapper {
    int CountSystemcase(@Param("casecheckstatus") Integer casecheckstatus);          // 根据casecheckstatus查看未处理系统案件数
    List<Systemcase> GetAllSystemcase(@Param("casecheckstatus") Integer casecheckstatus);
    //通过案件id来修改案件审核状态
    void UpdateCaseCheckStatusByCaseId(@Param("casecheckstatus") Integer casecheckstatus, @Param("caseid") String caseid);

    void DelSyscase(@Param("caseid") String caseid);

    int CountSystemcaseByCaseId(String caseid);

    Systemcase GetSystemCaseByCaseId(String case_id);
}
