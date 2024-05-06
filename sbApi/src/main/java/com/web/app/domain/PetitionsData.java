package com.web.app.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class PetitionsData {
    private String productName;

    private String productId;

    private String traderName;

    private String traderUrl;

    private String other;

    private String boughtDate;

    private float price;

    private String petitionTypeValue;

    private String petitionContext;

    private String expectResloveTypeValue;

    private String fileName;

    private String fileUrl;
}
