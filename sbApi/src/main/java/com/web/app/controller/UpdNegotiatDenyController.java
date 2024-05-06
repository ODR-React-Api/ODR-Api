package com.web.app.controller;

import org.springframework.web.bind.annotation.*;
import com.web.app.domain.Response;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(tags = "和解案拒否更新") 
@RestController
@RequestMapping("/negotiat")
@SuppressWarnings("rawtypes")
public class UpdNegotiatDenyController {
    @PostMapping("/updNegotiatDeny")
    public Response refusalNegotiations(@RequestBody String negotiationId){   
        try {

            System.out.println("后台访问成功");
            System.out.println(negotiationId);
            return AjaxResult.success("和解案更新成功!");
          } catch (Exception e) {
            AjaxResult.fatal("更新失败!", e);
            return null;
          }
    }
}
