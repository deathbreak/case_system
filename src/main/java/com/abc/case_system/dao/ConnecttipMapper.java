package com.abc.case_system.dao;

import com.abc.case_system.bean.Connecttip;
import com.abc.case_system.bean.ForRejectConnect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnecttipMapper {
        void AddNewConnect(@Param("cstatus") int cstatus, @Param("euser") String euser, @Param("ecaseid") String ecaseid, @Param("eid") int eid, @Param("ceid") String ceid);

        int CountConnectByUser(@Param("cstatus")int cstatus, @Param("cuser") String user);

        List<Integer> GetEidByCstatusUser( @Param("cstatus")int cstatus, @Param("cuser") String user);

        //判断是否可以进行修改操作,为0表示可以修改,为1代表管理员审批了
        int GetStatusByEid(@Param("eid") int eid);

        //根据status查询数量
        int CountByStatus(int cstatus);

        List<String> GetUserByStatus(int cstatus);

        List<Connecttip> GetConnectByStatus(int cstatus);

        List<Connecttip> GetConnectByStatusUser(int cstatus, String cuser);

        void UpdateConnectByEid(@Param("cstatus") int cstatus,@Param("canote") String canote,@Param("cunote") String cunote, @Param("eid") int eid);

        List<ForRejectConnect> GetUserConEvi(String cuser, int cstatus);

        List<Connecttip> GetConnectByStatusCaseId(int cstatus, String caseid);

        int CountByEid(int eid);

        int GetEidByCeid(String ceid);

        void UpdateEditEviToConnect(int eid, String ceid);
}
