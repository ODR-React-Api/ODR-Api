package com.web.app.domain.AnswerLoginConfirm;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * UpdCases
 * 
 * @author 李文涛
 * @since 2024/05/21
 * @version 1.0
 */
@ApiModel
@Data
public class UpdCases implements Serializable{

     
    private String cid;
   
    private String platformId;
   
    private Integer caseStage;
  
    private String caseStatus;
    
    private Boolean deleteFlag;
    
    private String traderUserEmail;
    // 代理人 mailaddress的list
    private List<String> traderagentuserList;
    // 反诉
    private Boolean phaseReply;
    // 谈判
    private Boolean phaseNegotiation;
    // 反诉标记
    private Integer counterclaimFlag;
    
    private String counterclaimStartDate;
    
    private String counterclaimEndDate;
    
    private String negotiationStartDate;
    
    private String negotiationEndDate;
   
    private Integer negotiationEndDateChangeStatus;
   
    private Integer negotiationEndDateChangeCount;
   
    private String cancelDate;

    private String lastModifiedDate;

    private String lastModifiedBy;
    // 系统时间
    private String newDate;
    // 系统时间+CounterclaimLimitDays
    private String oldDate;

    private static final long serialVersionUID = 1L;

}
