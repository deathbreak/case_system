package com.abc.case_system.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface WebuserMapper {

    int CountUsername(String username);

    String GetPasswordByUsername(String username);

}
