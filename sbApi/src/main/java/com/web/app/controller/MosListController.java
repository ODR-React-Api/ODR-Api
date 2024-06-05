package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.web.app.domain.getSelectListInfo.MosList;
import com.web.app.domain.getSelectListInfo.ReturnData;
import com.web.app.service.MosListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * S3_申立て一覧画面
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
@Api(tags = "申立て一覧画面")
@RestController
@RequestMapping("/mosList")

 /**
     * 検索用一覧取得
     * @author DUC 郝建润
     * @since 2024/06/04
     * @version 1.0
     */
public class MosListController{
    @Autowired
    private MosListService mosListService;
    @ApiOperation("検索用一覧取得")
    @GetMapping("/getSelectListInfo")
    public List<ReturnData> getSelectListInfo(MosList mosList){
        return mosListService.findCasePetitionUserId(mosList);
    }

}

