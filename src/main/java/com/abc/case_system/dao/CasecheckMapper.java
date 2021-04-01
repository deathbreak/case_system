package com.abc.case_system.dao;

import com.abc.case_system.bean.Casecheck;
import com.abc.case_system.bean.Idcard;
import com.abc.case_system.bean.Systemcase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasecheckMapper {
    int CountCasecheck();
    List<Casecheck> GetAllCasecheck();
    void UpdateSysCase(Systemcase systemcase);
    void UpdateInputCase(Systemcase systemcase);

    void DelInputcase(@Param("caseid") String caseid);

    int CountCaseCheckByCaseId(String check);

    void InputCaseCheck(Casecheck casecheck);

    List<Idcard> GetAllIdcardInfo();

    Casecheck GetCaseCheckByCaseId(String case_id);
}
