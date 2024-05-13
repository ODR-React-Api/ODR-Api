package com.web.app.domain.MosDetail;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RelationsContent implements Serializable {

  // 所属会社名
  private String petitionUsercompanyName;

  // 申立人氏名
  private String petitionUserName;

  // 申立人氏名（カナ）
  private String petitionUserkana;

  // 申立人Email
  private String petitionUserEmail;

  // 代理人1氏名
  private String Agent1Name;

  // 代理人1氏名（カナ）
  private String Agent1kana;

  // 代理人1Email
  private String Agent1Email;

  // 代理人1Flag
  private int Agent1Flag;

  // 代理人2氏名
  private String Agent2Name;

  // 代理人2氏名（カナ）
  private String Agent2kana;

  // 代理人2Email
  private String Agent2Email;

  // 代理人2Flag
  private int Agent2Flag;

  // 代理人3氏名
  private String Agent3Name;

  // 代理人3氏名（カナ）
  private String Agent3kana;

  // 代理人3Email
  private String Agent3Email;

  // 代理人3Flag
  private int Agent3Flag;

  // 代理人4氏名
  private String Agent4Name;

  // 代理人4氏名（カナ）
  private String Agent4kana;

  // 代理人4Email
  private String Agent4Email;

  // 代理人4Flag
  private int Agent4Flag;

  // 代理人5氏名
  private String Agent5Name;

  // 代理人5氏名（カナ）
  private String Agent5kana;

  // 代理人5Email
  private String Agent5Email;

  // 代理人5Flag
  private int Agent5Flag;

  // 所属会社名
  private String traderUsercompanyName;

  // 相手方氏名
  private String traderUserName;

  // 相手方氏名（カナ）
  private String traderUserkana;

  // 相手方Email
  private String traderUserEmail;

  // 代理人1氏名
  private String trader1Name;

  // 代理人1氏名（カナ）
  private String trader1kana;

  // 代理人1Email
  private String trader1Email;

  // 代理人1Flag
  private int trader1Flag;

  // 代理人2氏名
  private String trader2Name;

  // 代理人2氏名（カナ）
  private String trader2kana;

  // 代理人2Email
  private String trader2Email;

  // 代理人2Flag
  private int trader2Flag;

  // 代理人3氏名
  private String trader3Name;

  // 代理人3氏名（カナ）
  private String trader3kana;

  // 代理人3Email
  private String trader3Email;

  // 代理人3Flag
  private int trader3Flag;

  // 代理人4氏名
  private String trader4Name;

  // 代理人4氏名（カナ）
  private String trader4kana;

  // 代理人4Email
  private String trader4Email;

  // 代理人4Flag
  private int trader4Flag;

  // 代理人5氏名
  private String trader5Name;

  // 代理人5氏名（カナ）
  private String trader5kana;

  // 代理人5Email
  private String trader5Email;

  // 代理人5Flag
  private int trader5Flag;
}
