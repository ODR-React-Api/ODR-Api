package com.web.app.domain.AnswerLogin;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class PetitionsData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;

    private String productId;

    private String traderName;

    private String traderUrl;

    private String other;

    private String other01;

    private String other02;

    private String other03;

    private String other04;

    private String other05;

    private String boughtDate;

    private float price;

    private String petitionTypeValue;

    private String petitionContext;

    private String expectResloveTypeValue;

    private String fileName;

    private String fileUrl;
}
