package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.Response;
import com.web.app.domain.negotiatAgree.UpdNegotiatAgree;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.tool.AjaxResult;

/**
 * 和解案合意更新API
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块")
@RestController
@RequestMapping("/NegotiatAgree")
public class NegotiatAgreeController {
    @Autowired
    private NegotiatAgreeService negotiatAgreeService;
                                          
    /**
     * 
     * 和解案合意更新
     * 
     * @param updNegotiatAgree フォアグラウンドでんたつ
     * @return 和解案合意更新状態
     * @throws Exception 和解案合意更新失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("和解案合意更新")
    @PostMapping("/UpdNegotiatAgree")
    public Response UpdNegotiatAgree(@RequestBody UpdNegotiatAgree updNegotiatAgree) {
        try {
            // 和解案合意更新
            Boolean updateCount = negotiatAgreeService.updNegotiatAgree(updNegotiatAgree);
            if (updateCount == true) {
                return AjaxResult.success("和解案合意更新成功");
            }else{
                return AjaxResult.success("和解案合意更新失败");
            }
        } catch (Exception e) {
            AjaxResult.fatal("和解案合意更新異常", e);
            return null;
        }
    }
}