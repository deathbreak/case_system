package com.abc.case_system.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForMsgConnect {

    private Boolean flag;
    private String str;

    public static ForMsgConnect Update_connect(Integer eid,String replace_old, String replace_new, String caseconnect) {
        //1,a,,2,b,,3,c,,4,b,,5,a,,
        StringBuffer str_temp = new StringBuffer();
        String arr[] = caseconnect.split(",,");
        int flag = 0;

        for (String s : arr) {
            if (s.equals(eid + "," + replace_old)) {
                flag += 1;
                if ("".equals(replace_new)){
                    str_temp.append(replace_new);
                }else{
                    str_temp.append(eid + ","+ replace_new + ",,");
                }
            }else{
                str_temp.append(s + ",,");
            }
        }
        return new ForMsgConnect(flag == 0? false:true, str_temp.toString());
    }

    public static ForMsgConnect Update_edit_connect(int old_id, int new_id, String caseconnect){

        StringBuffer str_temp = new StringBuffer();
        String arr[] = caseconnect.split(",,");
        int flag = 0;

        for (String s : arr){
            if (s.equals(old_id + ",a")){
                flag += 1;
                str_temp.append(new_id + ",a,,");
            }else {
                str_temp.append(s+",,");
            }
        }

        if (flag == 0) throw new RuntimeException("证据信息被修改了~修改失败");
        return new ForMsgConnect(true, str_temp.toString());
    }
}

