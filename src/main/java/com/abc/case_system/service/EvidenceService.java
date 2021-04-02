package com.abc.case_system.service;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.dao.EvidenceMapper;
import com.abc.case_system.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvidenceService {
    @Autowired
    private EvidenceMapper evidenceMapper;

    public Boolean IsInputEviExist(Evidence evidence) {
        return evidenceMapper.CountEvidenceByTimeIdNote(evidence) > 0 ? true : false;
    }

    public void InsertNewEvidence(Evidence evidence){
        evidence.setElasttime(TimeInfo.get_now_time());
        evidence.setEupdate(1);
        evidence.setEstatus(1);
        evidenceMapper.InsertEvidence(evidence);
        evidence.setEid(evidence.getEcaseid()+evidence.getEidversion());
        evidence.setEhistory(evidence.getEidversion()+"");
        evidenceMapper.UpdateEidHisByKey(evidence);
    }
}
