package com.abc.case_system.service;

import com.abc.case_system.bean.Connecttip;
import com.abc.case_system.bean.Evidence;
import com.abc.case_system.dao.CaseMapper;
import com.abc.case_system.dao.ConnecttipMapper;
import com.abc.case_system.dao.EvidenceMapper;
import com.abc.case_system.utils.ForMsgConnect;
import com.abc.case_system.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvidenceService {
    @Autowired
    private EvidenceMapper evidenceMapper;

    @Autowired
    private ConnecttipMapper connecttipMapper;

    @Autowired
    private CaseMapper caseMapper;

    //添加新的证据
    public void InsertNewEvidence(Evidence evidence) {
        evidence.setElasttime(TimeInfo.get_now_time());
        evidence.setEupdate(1);
        evidence.setEstatus(1);
        evidenceMapper.InsertEvidence(evidence);
        evidence.setEid(evidence.getEcaseid() + evidence.getEidversion());
        evidence.setEhistory(evidence.getEidversion() + "");
        evidenceMapper.UpdateEidHisByKey(evidence);
        connecttipMapper.AddNewConnect(0, evidence.getEuser(), evidence.getEcaseid(), evidence.getEidversion());
        String caseconnect_check = caseMapper.QueryCaseconnectByCaseid(evidence.getEcaseid());
        caseMapper.UpdateCaseEvidence(evidence.getEcaseid(), (!"".equals(caseconnect_check) && null != caseconnect_check) ? caseconnect_check + evidence.getEidversion() + ",b,," : evidence.getEidversion() + ",b,,");
    }

    //通过查找数据库判断添加的证据信息是否已经存在
    public Boolean IsInputEviExist(Evidence evidence) {
        return evidenceMapper.CountEvidenceByTimeIdNote(evidence) > 0 ? true : false;
    }

    // 查询待关联的该用户证据数量
    public int CountConnectBySubmitUser(String username) {
        return connecttipMapper.CountConnectByUser(0, username);
    }

    // 查询待关联的该用户证据信息
    public List<Evidence> GetAllUserPendingConnectEvidence(String username) {
        List<Integer> eid = connecttipMapper.GetEidByCstatusUser(0, username);
        if (eid.size() > 0) {
            List<Evidence> result = new ArrayList<Evidence>();
            for (Integer id : eid) {
                result.add(evidenceMapper.GetEviByKey(id));
            }
            return result;
        } else {
            return null;
        }
    }

    //修改待关联的证据信息
    public Boolean UpdatePendingEvi(Evidence evidence) {
        if (connecttipMapper.GetStatusByEid(evidence.getEidversion()) == 0) {
            evidence.setElasttime(TimeInfo.get_now_time());
            evidenceMapper.UpdateUrlNoteLastTimeByKey(evidence);
            return true;
        } else {
            return false;
        }
    }

    public int CountByCstatus(int cstatus) {
        return connecttipMapper.CountByStatus(cstatus);
    }

    public List<String> GetUserByCstatus(int cstatus) {
        return connecttipMapper.GetUserByStatus(cstatus);
    }

    public List<Connecttip> GetConnectByCstatus(int cstatus){
        return connecttipMapper.GetConnectByStatus(cstatus);
    }

    public Evidence GetEviByEidversion(Integer eid) {
        return evidenceMapper.GetEviByKey(eid);
    }

    //管理员审核待关联证据状态变更
    public Boolean UpdatePendingEviStatus(Integer eid, String caseid){
        String str_ = caseMapper.GetCaseconnect(caseid);
        ForMsgConnect forMsgConnect = ForMsgConnect.Update_connect(eid, "b", "a", str_);
        if (forMsgConnect.getFlag()){
            caseMapper.UpdateCaseEvidence(caseid, forMsgConnect.getStr());
            connecttipMapper.UpdateCstatusByEid(1, eid);
            return true;
        }else{
            return false;
        }
    }
}
