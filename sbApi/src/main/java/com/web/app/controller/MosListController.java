package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.ReturnResult;
import com.web.app.service.MosListService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(tags = "API_一覧取得")
@RestController
@RequestMapping("/user")
/**
 * API_一覧取得
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
public class MosListController {

  @Autowired
  private MosListService getListInfoService;

  /**
   * メソッドの説明内容
   *
   * @param param1 セッション.ユーザID
   * @return API_一覧取得の取得内容
   * @throws Exception 
   */
  @ApiOperation("ケース检索")
  @PostMapping("/getListInfo")
  public Response<List> User(@RequestBody String uid) {
    // 戻り値初期化
    List<ReturnResult> returnResultList = new ArrayList<>();
    try {
      returnResultList = getListInfoService.getListInfo(uid);
      // 戻り値
      return AjaxResult.success("查询成功", returnResultList);
      // 異常な場合
    } catch (Exception e) {
      // 異常を処置した場合
      return AjaxResult.fatal("查询失败!", e);
    }
  }
}
