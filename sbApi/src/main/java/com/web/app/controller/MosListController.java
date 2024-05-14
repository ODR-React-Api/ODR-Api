package com.web.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.SelectListInfoResult;
import com.web.app.service.MosListService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S3_申立て一覧画面
 * Controller層
 * MosListController
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立て一覧画面")
@RestController
@RequestMapping("/mosList")
public class MosListController {
  @Autowired
  private MosListService mosListService;

  /**
   * 検索条件の引数によって、一覧データを取得する。
   *
   * @param position 検索サブ画面で入力の画面項目
   * @return SelectListInfoResult 一覧画面表示用のデータ
   * @throws Exception 検索失敗時異常
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("検索用一覧取得")
  @PostMapping("/getSelectListInfo")
  public Response selectListInfo(@RequestBody Position position) {

    try {
      // 検索用一覧取得
      List<SelectListInfoResult> selectListList = mosListService.getSelectListInfo(position);
      if (selectListList.size() > 0) {
        return AjaxResult.success("検索成功!", selectListList);
      } else {
        return AjaxResult.success("検索0件!");
      }
    } catch (Exception e) {
      AjaxResult.fatal("検索異常!", e);
      return null;
    }
  }
}