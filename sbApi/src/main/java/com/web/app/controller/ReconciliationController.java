package com.web.app.controller;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.ReconciliationService;

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块") 
@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {

//   @Autowired
//   DataSource dataSource;

  @Autowired
  private ReconciliationService ReconciliationSerce;

  @ApiOperation("「和解案」更新")
  @PostMapping("/reconciliationInsertUpdateDeleteTransactional")
  public ReconciliationUser reconciliationInsertUpdateDeleteTransactional(@RequestBody ReconciliationUser reconciliationuser) {
    try {
        reconciliationuser = ReconciliationSerce.reconciliationInsertUpdateDeleteTransactional(reconciliationuser);
        return reconciliationuser;     
    } catch (Exception e) {
      AjaxResult.fatal("「和解案」更新失败!", e);
      return null;
    }
  }
}
