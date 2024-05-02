package com.web.app.domain;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;
@ApiModel 
@Data
public class GetPreUserData implements Serializable {
  private static final long serialVersionUID = 1L;

    private String email;
}








// /**
//  * odr_users_pre
//  */
// @ApiModel // 声明当前对象是用来封装数据的对象
// @Data
// public class OdrUsersPre implements Serializable {


//     private String id;

//     private String platformId;

//     private String email;

//     private String guid;

//     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//     private LocalDateTime registerDate;

//     private String other01;

//     private String other02;

//     private String other03;

//     private String other04;

//     private String other05;

//     private Integer deleteFlage;

//     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//     private LocalDateTime lastModifiedDate;

//     private String lastModifiedBy;

//     private static final long serialVersionUID = 1L;
// }