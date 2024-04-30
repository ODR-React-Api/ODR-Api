package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class PetitionInfo  implements Serializable{
    private static final long serialVersionUID = 1L;
    //名前
    private String firstName;
    //名字
    private String lastName;
    //名前　カナ
    private String firstName_kana;
    //名字　カナ
    private String lastName_kana;
    //所属会社名
    private String companyName;   
}

