package com.web.app.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;
@ApiModel 
@Data
public class GetRepliesContext implements Serializable{
      private static final long serialVersionUID = 1L;

      private String CounterClaimContext;

      private String FileName;

      private String FileUrl;

}

