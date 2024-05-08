package com.web.app.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class Caserelations implements Serializable {

    // frontからmailaddressのlist
    public List<String> traderagentuserList;
    // frontからmailaddress---五つまで補足後のlist
    public List<String> traderagentuserListAll;

    // 案件ID
    private String caseId;
    // プラットフォームID
    private String platformId;

    // public int updateCaserelations(Caserelations caserelations) {
    //     return caserelationsMapper.updateCaserelations(caserelations);
    // }
    // //相手方代理人1
    // private String TraderAgent1_UserEmail;
    // //相手方代理人2
    // private String TraderAgent2_UserEmail;
    // //相手方代理人3
    // private String TraderAgent3_UserEmail;
    // //相手方代理人4
    // private String TraderAgent4_UserEmail;
    // //相手方代理人5
    // private String TraderAgent5_UserEmail;

    private static final long serialVersionUID = 1L;
}
