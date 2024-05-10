package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.updNegotiatDeny.Negotiation;
import com.web.app.service.UpdNegotiatDenyService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;

/**
 * 和解案拒否更新API Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "和解案拒否更新")
@RestController
@RequestMapping("/negotiat")
@SuppressWarnings("rawtypes")
public class UpdNegotiatDenyController {

    //サービスオブジェクト
    @Autowired
    private UpdNegotiatDenyService updNegotiatDenyService;

    /**
     * 
     * サービスメソッドを呼び出して和解案を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
    @PostMapping("/updNegotiatDeny")
    public Response refusalNegotiations(@RequestBody Negotiation negotiation) {
        try {
            // 和解案が更新されたかどうかを判断する
            if (updNegotiatDenyService.updateNegotiatData(negotiation) != 0) {
                return AjaxResult.success("和解案が更新されました!");
            }
            return AjaxResult.success("和解案が更新されませんでした!");
        } catch (Exception e) {
            AjaxResult.fatal("更新に失敗しました!", e);
            return null;
        }
    }
}
