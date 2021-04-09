package com.abc.case_system.service;

import com.abc.case_system.bean.Connecttip;
import com.abc.case_system.bean.Evidence;
import com.abc.case_system.bean.ForRejectConnect;
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

    //修改退回关联的证据信息
    public void UpdateRejectEvi(Evidence evidence){
        evidence.setElasttime(TimeInfo.get_now_time());
        evidenceMapper.UpdateUrlNoteLastTimeByKey(evidence);
    }

    public int CountByCstatus(int cstatus) {
        return connecttipMapper.CountByStatus(cstatus);
    }

    public List<String> GetUserByCstatus(int cstatus) {
        return connecttipMapper.GetUserByStatus(cstatus);
    }

    public List<Connecttip> GetConnectByCstatus(int cstatus) {
        return connecttipMapper.GetConnectByStatus(cstatus);
    }

    public Evidence GetEviByEidversion(Integer eid) {
        return evidenceMapper.GetEviByKey(eid);
    }

    /*
    *   管理员审核关联证据状态及信息变更   这里Boolean是为了判定并发环境下的查了被修改的问题,然后加了个synchronized避免了这种情况,
    *                         使得这个Boolean没了意义,但是高并发的环境下 synchronized 会影响处理速度,还是要删掉了使用
    *                          Boolean来判定,但是这个项目不会出现高并发的情况所以就用了 锁synchronized
    */
    public synchronized Boolean UpdateEviConnect(Integer eid, String caseid, Integer update_status, String canote, String cunote, String replace_old, String replace_new) {
        String str_ = caseMapper.GetCaseconnect(caseid);
        ForMsgConnect forMsgConnect = ForMsgConnect.Update_connect(eid, replace_old, replace_new, str_);
        if (forMsgConnect.getFlag()) {
            caseMapper.UpdateCaseEvidence(caseid, forMsgConnect.getStr());
            connecttipMapper.UpdateConnectByEid(update_status, canote, cunote, eid);
            return true;
        } else {
            return false;
        }
    }

    // 查询用户被退回的证据关联信息
    public List<ForRejectConnect> GetAllUserEvi(String cuser, int cstatus){
        return connecttipMapper.GetUserConEvi(cuser, cstatus);
    }
}
