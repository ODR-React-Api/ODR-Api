package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class CaseRelations implements Serializable {
  // 案件ID
  private String caseId;

  // 申立人メール
  private String petitionUserInfoEmail;

  // 代理人1メール
  private String agent1Email;

  // 代理人2メール
  private String agent2Email;

  // 代理人3メール
  private String agent3Email;

  // 代理人4メール
  private String agent4Email;

  // 代理人5メール
  private String agent5Email;

  // 相手方メール
  private String traderUserEmail;

  // 相手方代理人1メール
  private String traderAgent1UserEmail;

  // 相手方代理人2メール
  private String traderAgent2UserEmail;

  // 相手方代理人3メール
  private String traderAgent3UserEmail;

  // 相手方代理人4メール
  private String traderAgent4UserEmail;

  // 相手方代理人5メール
  private String traderAgent5UserEmail;
}