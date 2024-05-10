package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.UpdNegotiatAgreeService;

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
@RequestMapping("/updnegotiatagree")
public class UpdNegotiatAgreeController {

    @Autowired
    private UpdNegotiatAgreeService ReconciliationSerce;

    /**
    * 
    * 和解案合意更新
    * 
    * @param reconciliationuser フォアグラウンドでんたつ
    * @return 合意の成否を判断する
    * @throws Exception 合意失敗
    */
    @ApiOperation("和解案合意更新")
    @PostMapping("/reconciliationUpdate")
    public int reconciliationUpdate(@RequestBody ReconciliationUser reconciliationuser) {
        try {
            // 和解案合意更新
            int ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
            return ReconciliationUpdateStatus;
        } catch (Exception e) {
            return 0;
        }
  }
}