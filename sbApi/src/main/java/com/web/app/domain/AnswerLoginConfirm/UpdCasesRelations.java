package com.web.app.domain.AnswerLoginConfirm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * UpdCasesRelations
 * 
 * @author 李文涛
 * @since 2024/05/21
 * @version 1.0
 */
@Data
public class UpdCasesRelations implements Serializable{

    @SuppressWarnings("unused")
    private static final long SerialVersionUID=1L;

    private List<String> traderagentuserList;  
    
    private String caseId;
    
    private String platformId;
}
