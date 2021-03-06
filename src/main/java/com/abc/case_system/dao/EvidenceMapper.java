package com.abc.case_system.dao;

import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.Forurl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceMapper {

    int CountEvidenceByTimeIdNote(Evidence evidence);

    int InsertEvidence(Evidence evidence);

    void UpdateEidHisByKey(Evidence evidence);

    Evidence GetEviByKey(int eidversion);

    void UpdateUrlNoteLastTimeByKey(Evidence evidence);

    void UpdateTextUrlNoteLastTimeByKey(Evidence evidence);

    void UpdateEviEstatus(int eidversion, int estatus);

    void UpdateNewVersionEvi(int eidversion, int eupdate, String ehistory);

    int GetEupdateBykey(int eidversion);

    void UpdateEviEupdateEtext(int eidversion, int eupdate, String etext);

    int CountEviByNotEupdateOne(String eid);

    int CountEviByEupdate2(String eid);

    Evidence GetEviByEditEvi(int eidversion, String eid);

    void InsertEditEvidence(Evidence evidence);

    List<Evidence> GetEviByEupdateEstatus(int eupdate, int estatus);

    String GetEidByKey(int eidversion);

    Forurl GetUrlByKey(int eidversion);

    List<Evidence> GetRejectEvi(String euser, int eupdate);
}
