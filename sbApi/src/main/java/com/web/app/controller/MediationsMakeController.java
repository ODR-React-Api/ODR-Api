package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Mediationcase;
import com.web.app.service.MediationsMakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "調停案作成模块")
@RestController
@RequestMapping("/mediationcase")
public class MediationsMakeController {
    @Autowired
    private MediationsMakeService mediationcaseService;

    @ApiOperation("調停案データ新規登録")
    @PostMapping("/MediationcaseInsert")
    public int MediationcaseInsert(@RequestBody Mediationcase mediationcase) {
        try {
            //判断数据是否已经存在
            List<Mediationcase> mediationcaseSearch = mediationcaseService.MediationcaseSearch(mediationcase);

            int num = mediationcaseService.MediationcaseInsert(mediationcase);
            return num;
        } catch (Exception e) {
            return 0;
        }

    }
}
