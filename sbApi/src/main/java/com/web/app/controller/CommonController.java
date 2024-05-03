package com.web.app.controller;

import java.util.Date;
import java.util.UUID;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.web.app.domain.ActionHistories;
// import com.web.app.domain.Constants;
import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.constants.Constants;
import com.web.app.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "Common") // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@RestController
@RequestMapping("/Common")
public class CommonController {
    @Autowired
    DataSource dataSource;

    @Autowired
    private CommonService commonService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人/相手方)")
    @GetMapping("/GetUserDataFromCaseIdentity")
    // identity = true 申立人;identity = false 相手方
    public Response GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId)
            throws Exception {
        try {
            Response dataResponse = new Response<User>();
            System.out.println("获取的数据库连接为:" + dataSource.getConnection());
            User user = commonService.GetUserDataFromCaseIdentity(identity, languageId, platformId, caseId);
            dataResponse.setCode(Constants.RETCD_SUCCESS);
            dataResponse.setMsg(Constants.RETCD_OK);
            dataResponse.setData(user);
            return dataResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation("アクション履歴新規登録")
    @GetMapping("/InsHistories")
    public Response InsHistories(ActionHistories actionHistories, List<String> fileId, Boolean parametersFlag,
            Boolean displayNameFlag) throws Exception {
        Response dataResponse = new Response<>();
        try {
            actionHistories.setId(UUID.randomUUID().toString());
            actionHistories.setActionDateTime(new Date());
            int res = commonService.InsHistories(actionHistories, fileId, parametersFlag, displayNameFlag);
            if (res == 1) {
                dataResponse.setData(res);
                dataResponse.setCode(Constants.RETCD_SUCCESS);
                dataResponse.setMsg(Constants.RETCD_OK);
            }
            return dataResponse;
        } catch (Exception e) {
            throw e;
        }
    }
}