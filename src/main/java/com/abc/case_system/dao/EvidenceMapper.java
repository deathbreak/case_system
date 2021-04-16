package com.abc.case_system.dao;

import com.abc.case_system.bean.Evidence;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceMapper {

    int CountEvidenceByTimeIdNote(Evidence evidence);

    int InsertEvidence(Evidence evidence);

    void UpdateEidHisByKey(Evidence evidence);

    Evidence GetEviByKey(int eidversion);

    void UpdateUrlNoteLastTimeByKey(Evidence evidence);

    void UpdateEviEstatus(int eidversion, int estatus);

    int CountEviByNotEupdateOne(String eid);

    Evidence GetEviByEditEvi(int eidversion, String eid);
}
