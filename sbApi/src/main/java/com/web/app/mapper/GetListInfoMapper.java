package com.web.app.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosDetail.UserCase;

/**
 * 检索odr_users表
 */
@Mapper
public interface GetListInfoMapper {

  // ユーザ情報を取得する
  String selectEmailOdrUsers(String uid);

  // ユーザが申立人となるケースの取得
  List<UserCase> selectCaseIdPetition1(String email);

  // ユーザが相手方とするケースの取得
  List<UserCase> selectCaseIdPetition2(String email);

  // ユーザが調停人となるケースの取得 
  List<UserCase> selectCaseIdPetition3(String email);
}
