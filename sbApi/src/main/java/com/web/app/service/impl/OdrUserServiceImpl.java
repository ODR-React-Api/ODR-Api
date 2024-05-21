package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.mapper.OdrUserMapper;
import com.web.app.service.OdrUserService;
import com.web.app.tool.AjaxResult;

@Service
public class OdrUserServiceImpl implements OdrUserService {
    @Autowired
    OdrUserMapper odrUserMapper;

     /*
   * 测试事务的使用
   */
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  @Override
  @SuppressWarnings("rawtypes")
  public Response addUser(OdrUserUtil odrUserUtil) {
    int result = odrUserMapper.insertUser(odrUserUtil);
    // System.out.println(1/0);
    if (result != 1) {
      return AjaxResult.error("添加用户失败!");
    }
    return AjaxResult.error("添加用户成功!");
  }
    
    

}
