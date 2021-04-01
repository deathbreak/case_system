package com.abc.case_system.dao;

import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    int CountUserByUPR(String username, String password, Integer role);
    int CountUserByUR(String username, Integer role);
}
