package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.Negotiation;
import com.web.app.domain.Response;
import com.web.app.service.UpdNegotiatDenyService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(tags = "和解案拒否更新") 
@RestController
@RequestMapping("/negotiat")
@SuppressWarnings("rawtypes")
public class UpdNegotiatDenyController {

  @Autowired
  private UpdNegotiatDenyService updNegotiatDenyService;

  @PostMapping("/updNegotiatDeny")
  public Response refusalNegotiations(@RequestBody Negotiation negotiation){   
      try {
          if (updNegotiatDenyService.updateNegotiatData(negotiation) != 0) {
            return AjaxResult.success("和解案已更新!");
          }
          return AjaxResult.success("和解案未更新!");
        } catch (Exception e) {
          AjaxResult.fatal("更新失败!", e);
          return null;
        }
  }
}
