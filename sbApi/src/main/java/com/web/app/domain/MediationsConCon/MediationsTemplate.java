package com.web.app.domain.MediationsConCon;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;

@ApiModel 
@Data
public class MediationsTemplate implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String context;

    private String platformId;

    private String languageId;


}
