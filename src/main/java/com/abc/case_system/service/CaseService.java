package com.abc.case_system.service;

import com.abc.case_system.bean.Case;
import com.abc.case_system.dao.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {
    @Autowired
    private CaseMapper caseMapper;

    public List<Case> GetCaseByQuery(String querystr){
        if ("".equals(querystr) || querystr == null){
            return caseMapper.GetAllCase();
        }else{
            int check_count = caseMapper.CountQueryByIdCardAndCaseId(querystr);
            if (check_count != 0){
                return caseMapper.QueryByIdCardAndCaseId(querystr);
            }else{
                return null;
            }
        }
    }


    public void UpdateCase(Case aCase) {
        caseMapper.UpdateCaseInfo(aCase);
    }

    public List<Case> GetAllCaseId() {
        return caseMapper.GetAllCaseIdAndCaseName();
    }
}
