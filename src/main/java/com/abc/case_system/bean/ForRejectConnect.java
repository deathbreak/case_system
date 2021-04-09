package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForRejectConnect {

    private Integer cstatus;
    private String cuser;
    private String caseid;
    private Integer eid;
    private String canote;
    private String cunote;
    private String eurl;
    private String enote;
}
