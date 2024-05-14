package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * 申立て登録画面の初期画面全ての内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class PictureDisplay implements Serializable {
    private static final long serialVersionUID = 1L;

    //「API_画面制御表示項目取得」の戻り値
    GetPetitionTemp getPetitionTemp = new GetPetitionTemp();

    //「API_申立て下書き保存データ取得」の戻り値
    GetPlatform getPlatform = new GetPlatform();
}
