package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdNegotiatCon implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;

    private String negotiationId;

    private Integer status;

    private String lastModifiedDate;

    private String loginUser;

    private String caseId;

    private String petitionUserInfoEmail;

    private String agent1Email;

    private String agent2Email;

    private String agent3Email;

    private String agent4Email;

    private String agent5Email;

    private String traderUserEmail;

    private String traderAgent1UserEmail;

    private String traderAgent2UserEmail;

    private String traderAgent3UserEmail;

    private String traderAgent4UserEmail;

    private String traderAgent5UserEmail;

    private String mediatorUserEmail;

     private String email;

}
