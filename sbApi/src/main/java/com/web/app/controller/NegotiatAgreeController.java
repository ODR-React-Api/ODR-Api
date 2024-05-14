package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.negotiatAgree.Negotiation;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案合意画面 Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意画面")
@RestController
@RequestMapping("/NegotiatAgree")
@SuppressWarnings("rawtypes")
public class NegotiatAgreeController {

    //サービスオブジェクト
    @Autowired
    private NegotiatAgreeService negotiatAgreeService;

    /**
     * 
     * API_ID:和解案拒否更新
     * 
     * サービスメソッドを呼び出して和解案を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
    @ApiOperation("和解案拒否更新")
    @PostMapping("/updNegotiatDeny")
    public Response refusalNegotiations(@RequestBody Negotiation negotiation) {
        try {
            // 和解案が更新されたかどうかを判断する
            if (negotiatAgreeService.updNegotiatDeny(negotiation) != 0) {
                return AjaxResult.success("和解案が更新されました!");
            }
            return AjaxResult.success("和解案が更新されませんでした!");
        } catch (Exception e) {
            AjaxResult.fatal("更新に失敗しました!", e);
            return null;
        }
    }
}
