package com.abc.case_system.dao;

import com.abc.case_system.bean.Connecttip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnecttipMapper {
        void AddNewConnect(@Param("cstatus") int cstatus, @Param("euser") String euser, @Param("ecaseid") String ecaseid, @Param("eid") int eid);

        int CountConnectByUser(@Param("cstatus")int cstatus, @Param("cuser") String user);

        List<Integer> GetEidByCstatusUser( @Param("cstatus")int cstatus, @Param("cuser") String user);

        //判断是否可以进行修改操作,为0表示可以修改,为1代表管理员审批了
        int GetStatusByEid(@Param("eid") int eid);

        //根据status查询数量
        int CountByStatus(int cstatus);

        List<String> GetUserByStatus(int cstatus);

        List<Connecttip> GetConnectByStatus(int cstatus);

        void UpdateCstatusByEid(@Param("cstatus") int cstatus, @Param("eid") int eid);
}
