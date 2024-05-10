package com.web.app.domain.MediationsConCon;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;

@ApiModel
@Data
public class MediationsUserData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String firstName;

    private String middleName;

    private String lastName;

    private String companyName;

}
