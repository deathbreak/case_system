package com.abc.case_system.dao;


import com.abc.case_system.bean.Webdata;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebdataMapper {

    void AddNewOne(Webdata webdata);

    List<Webdata> GetAllUserEvi(String submituser);


    List<Webdata> GetAllEvi();

    void UpdateStatus(String checkstat);


}
