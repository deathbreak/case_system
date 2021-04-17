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
        connecttipMapper.AddNewConnect(0, evidence.getEuser(), evidence.getEcaseid(), evidence.getEidversion(), evidence.getEid());
        String caseconnect_check = caseMapper.QueryCaseconnectByCaseid(evidence.getEcaseid());
        caseMapper.UpdateCaseEvidence(evidence.getEcaseid(), (!"".equals(caseconnect_check) && null != caseconnect_check) ? caseconnect_check + evidence.getEidversion() + ",b,," : evidence.getEidversion() + ",b,,");
    }

    //通过查找数据库判断添加的证据信息是否已经存在
    public Boolean IsInputEviExist(Evidence evidence) {
        return evidenceMapper.CountEvidenceByTimeIdNote(evidence) > 0 ? true : false;
    }

    // 通过状态查询该用户证据数量
    public int CountConnectBySubmitUser(int cstatus, String username) {
        return connecttipMapper.CountConnectByUser(cstatus, username);
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
    public void UpdateRejectEvi(Evidence evidence, String caseid, String cunote) {
        ForMsgConnect forMsgConnect = IsReplaceStrTrue(caseid, evidence.getEidversion(), "c", "b");
        if (forMsgConnect.getFlag()) {
            caseMapper.UpdateCaseEvidence(caseid, forMsgConnect.getStr());
            connecttipMapper.UpdateConnectByEid(0, "", cunote, evidence.getEidversion());
            evidence.setElasttime(TimeInfo.get_now_time());
            evidenceMapper.UpdateUrlNoteLastTimeByKey(evidence);
        }
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
        ForMsgConnect forMsgConnect = IsReplaceStrTrue(caseid, eid, replace_old, replace_new);
        if (forMsgConnect.getFlag()) {
            caseMapper.UpdateCaseEvidence(caseid, forMsgConnect.getStr());
            connecttipMapper.UpdateConnectByEid(update_status, canote, cunote, eid);
            return true;
        } else {
            return false;
        }
    }

    // 查询用户被退回的证据关联信息
    public List<ForRejectConnect> GetAllUserEvi(String cuser, int cstatus) {
        return connecttipMapper.GetUserConEvi(cuser, cstatus);
    }

    //解除关联
    public void DisConnect(Integer eid, String caseid) {
        int check_status = connecttipMapper.GetStatusByEid(eid);
        if (check_status == 2) {
            ForMsgConnect forMsgConnect = IsReplaceStrTrue(caseid, eid, "c", "");
            caseMapper.UpdateCaseEvidence(caseid, forMsgConnect.getStr());
            connecttipMapper.UpdateConnectByEid(4, "", "", eid);
            evidenceMapper.UpdateEviEstatus(eid, 0);
        }
    }

    public ForMsgConnect IsReplaceStrTrue(String caseid, Integer eid, String replace_old, String replace_new) {
        String str_ = caseMapper.GetCaseconnect(caseid);
        return ForMsgConnect.Update_connect(eid, replace_old, replace_new, str_);
    }

    public List<Connecttip> GetConByQueryCaseid(String query) {
        if ("".equals(query) || null == query) {
            return connecttipMapper.GetConnectByStatus(1);
        } else {
            List<Connecttip> check = connecttipMapper.GetConnectByStatusCaseId(1, query);
            return check.size() == 0 ? null : check;
        }
    }

    public List<Connecttip> GetUserConByStatus(int status, String username) {
        return connecttipMapper.GetConnectByStatusUser(status, username);
    }

    public Boolean IsExistEid(int eid) {
        return connecttipMapper.CountByEid(eid) == 1 ? true : false;
    }

    // 判断是否有多个修改版本
    public Boolean IsMoreEidVersion(String eid) {
        return evidenceMapper.CountEviByNotEupdateOne(eid) > 0 ? true : false;
    }

    public Evidence GetEditEviByEid(String eid) {
        int eidversion = connecttipMapper.GetEidByCeid(eid);
        if (IsMoreEidVersion(eid)) {
            return evidenceMapper.GetEviByEditEvi(eidversion, eid);
        } else {
            return evidenceMapper.GetEviByKey(eidversion);
        }
    }

//    // 废弃的方法  现在采用前端检测
//    public Boolean IsEdit(int eidversion, String eurl, String enote) {
//        Evidence evidence = evidenceMapper.GetEviByKey(eidversion);
//        return !(evidence.getEurl().equals(eurl) && evidence.getEnote().equals(enote));
//    }

    public Boolean UpdateEditEvi(int flag, Evidence evidence){    //flag = 1 --> 已有修改   flag = 2 --> 没有
        int check_eid = connecttipMapper.GetEidByCeid(evidence.getEid());
        boolean check_ismore = IsMoreEidVersion(evidence.getEid());
        if (flag == 1 && check_eid < evidence.getEidversion() && check_ismore) {
            evidence.setElasttime(TimeInfo.get_now_time());
            evidenceMapper.UpdateUrlNoteLastTimeByKey(evidence);
            return true;
        }
        if (flag == 2 && !check_ismore){
            Evidence evidence_insert = evidenceMapper.GetEviByKey(evidence.getEidversion());
            evidence_insert.setElasttime(TimeInfo.get_now_time());
            evidence_insert.setEupdate(0);
            evidence_insert.setEurl(evidence.getEurl());
            evidence_insert.setEnote(evidence.getEnote());
            evidenceMapper.InsertEditEvidence(evidence_insert);
            return true;
        }
        return false;
    }

    public List<Evidence> GetEditEviByEupdateStat(int eupdate, int estatus){
        return evidenceMapper.GetEviByEupdateEstatus(eupdate, estatus);
    }

}
