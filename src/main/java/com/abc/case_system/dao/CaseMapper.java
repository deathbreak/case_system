package com.abc.case_system.dao;

import com.abc.case_system.bean.Case;
import com.abc.case_system.bean.Casecheck;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseMapper {
    int CountCaseByCaseId(String caseid);

    void InputCase(Casecheck casecheck);

    int CountCase();

    List<Case> GetAllCase();

    List<Case> QueryByIdCardAndCaseId(String str);

    int CountQueryByIdCardAndCaseId(String str);

    void UpdateCaseInfo(Case aCase);

//    List<Case> QueryByCaseid(String caseid);

//    int CountQueryByCaseid(String caseid);
}
