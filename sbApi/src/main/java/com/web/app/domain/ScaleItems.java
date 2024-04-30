package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class ScaleItems  implements Serializable{
    private static final long serialVersionUID = 1L;
    //拡張項目Id
    private String extensionitemId;
    //拡張項目値
    private String extensionitemValue;
}

