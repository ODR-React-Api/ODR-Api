package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.service.NamAcceptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "指名受理画面")
@RestController
@RequestMapping("/NamAccept")
public class NamAcceptController {

    @Autowired
    private NamAcceptService namAcceptService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人変更履歴変更API")
    @PostMapping("/updMediatorHistories")
    public Response updMediatorHistories(@RequestBody UpdMediatorHistories updMediatorHistories) {

        int updMediatorHistoriesNum = namAcceptService.UpdMediatorHistories(updMediatorHistories);

        if(updMediatorHistoriesNum == 1) {
            return Response.success("成功");
        }
        return Response.error("失败");
    }

}
