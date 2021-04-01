package com.abc.case_system.service;

import com.abc.case_system.bean.Casecheck;
import com.abc.case_system.bean.Idcard;
import com.abc.case_system.bean.Systemcase;
import com.abc.case_system.dao.CaseMapper;
import com.abc.case_system.dao.CasecheckMapper;
import com.abc.case_system.dao.SystemcaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CasecheckService {
    @Autowired
    private SystemcaseMapper systemcaseMapper;

    @Autowired
    private CasecheckMapper casecheckMapper;

    @Autowired
    private CaseMapper caseMapper;


    // 查询待复核的案件数
    public int GetIntCheckCase() {
        return systemcaseMapper.CountSystemcase(1) + casecheckMapper.CountCasecheck();
    }

    // 获取所有手工录入的案件
    public List<Casecheck> GetAllCaseCheck() {
        return casecheckMapper.GetAllCasecheck();
    }

    // 修改
    public void UpdateCheckCase(String flag, Systemcase systemcase) {
        if ("0".equals(flag)) {
            casecheckMapper.UpdateSysCase(systemcase);
        } else {
            casecheckMapper.UpdateInputCase(systemcase);
        }
    }

    //删除审核中的案件信息
    public void DelCheckCase(String flag, String case_id) {
        if ("0".equals(flag)) {
            systemcaseMapper.DelSyscase(case_id);
        } else {
            casecheckMapper.DelInputcase(case_id);
        }
    }

    public Boolean InputCaseService(Casecheck casecheck) {
        String check = casecheck.getCaseid();
        int count = casecheckMapper.CountCaseCheckByCaseId(check) + systemcaseMapper.CountSystemcaseByCaseId(check) + caseMapper.CountCaseByCaseId(check);
        if (count == 0) {
            casecheckMapper.InputCaseCheck(casecheck);
            return true;
        } else {
            return false;
        }
    }

    public List<Idcard> GetAllIdcard() {
        return casecheckMapper.GetAllIdcardInfo();
    }

    public void InputCase(String flag, String case_id) {
        if ("0".equals(flag)) {
            Systemcase systemcase = systemcaseMapper.GetSystemCaseByCaseId(case_id);
            Casecheck casecheck = new Casecheck(systemcase.getCaseid(), systemcase.getCasetime(), systemcase.getCasename(), systemcase.getCaseinfo(), systemcase.getCasepeople(), systemcase.getCasestatus(), systemcase.getCasenote(), null);
            caseMapper.InputCase(casecheck);
            systemcaseMapper.DelSyscase(case_id);
        } else {
            caseMapper.InputCase(casecheckMapper.GetCaseCheckByCaseId(case_id));
            casecheckMapper.DelInputcase(case_id);
        }
    }
}
