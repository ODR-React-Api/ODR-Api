package com.web.app.service.impl;
//这里加一行用来测试git commit
import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.mapper.UserMapper;
import com.web.app.service.UserService;
import com.web.app.tool.AjaxResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> findAllUser() {
    return userMapper.selectAllUser();
  }

  @Override
  public PageInfo<User> findAllUserByPageHelper(Integer pageNum, Integer pageSize) {
    try {
      PageHelper.startPage(pageNum, pageSize);
      List<User> userList = userMapper.selectAllUser();
      PageInfo<User> pageInfo = new PageInfo<>(userList);
      return pageInfo;
    } catch (Exception e) {
      return null;
    }
  }

  /*
   * 测试事务的使用
   */
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  @Override
  @SuppressWarnings("rawtypes")
  public Response addUser(User user) {
    int result = userMapper.insertUser(user);
    // System.out.println(1/0);
    if (result != 1) {
      return AjaxResult.error("添加用户失败!");
    }
    return AjaxResult.error("添加用户成功!");
  }
}
