package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.UpdNegotiatAgree.ReconciliationUser;
import com.web.app.service.NegotiatAgreeService;

/**
 * 和解案合意更新API
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块")
@RestController
@RequestMapping("/NegotiatAgree")
public class NegotiatAgreeController {

    @Autowired
    private NegotiatAgreeService ReconciliationSerce;

    /**
     * 
     * 和解案合意更新
     * 
     * @param reconciliationuser フォアグラウンドでんたつ
     * @return 和解案合意更新状態 1：更新に成功しました,0：更新失败
     * @throws Exception 和解案合意更新失敗
     */
    @ApiOperation("和解案合意更新")
    @PostMapping("/UpdNegotiatAgree")
    public int UpdNegotiatAgree(@RequestBody ReconciliationUser reconciliationuser) {
        try {
            // 和解案合意更新
            int ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
            return ReconciliationUpdateStatus;
        } catch (Exception e) {
            return 0;
        }
    }
}