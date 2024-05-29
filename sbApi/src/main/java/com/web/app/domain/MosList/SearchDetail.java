package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cid;

    private Integer caseStage;

    private String caseStatus;

    private String caseTitle;

    private String petitonDate;

    private String replyEndDate;

    private String conuterclaimEndDate;

    private String negotiationEndDate;

    private String mediationEndDate;

    private Integer negotiationEndDateChangeStatus;

    private Integer status;

    private Integer groupMessageFlag1;

    private Integer groupMessageFlag2;

    private Integer mediationsStatus;
}
