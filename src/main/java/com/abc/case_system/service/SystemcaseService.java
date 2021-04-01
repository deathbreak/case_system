package com.abc.case_system.service;

import com.abc.case_system.bean.Systemcase;
import com.abc.case_system.dao.SystemcaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemcaseService {
    @Autowired
    private SystemcaseMapper systemcaseMapper;

    // 根据casecheckstatus查  表示未处理案件  `casecheckstatus` int   0/1/2  未处理/在审核/已入库
    public List<Systemcase> GetAllSystemcase(Integer casecheckstatus){
        return systemcaseMapper.GetAllSystemcase(casecheckstatus);
    }
    // 根据casecheckstatus查  表示未处理案件  `casecheckstatus` int   0/1/2  未处理/在审核/已入库
    public int GetIntAllSystemcase(){
        return systemcaseMapper.CountSystemcase(0);
    }
    // 根据案件编号来更改案件审核状态
    public void UpdateCaseCheckStatus(Integer casecheckstatus, String caseid){
        systemcaseMapper.UpdateCaseCheckStatusByCaseId(casecheckstatus, caseid);
    }

}
