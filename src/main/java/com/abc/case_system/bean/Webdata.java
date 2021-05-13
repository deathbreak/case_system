package com.abc.case_system.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Webdata {
    private String datatime;
    private String caseinfo;
    private String telephone;
    private String submituser;
    private String url;
    private String note;
    private int status;
    private String checkstat;
}
