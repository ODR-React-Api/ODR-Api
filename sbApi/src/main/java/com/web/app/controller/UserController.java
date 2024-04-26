package com.web.app.controller;

import com.web.app.domain.TestUser;
import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.service.TestUserService;
import com.web.app.service.UserService;
import com.web.app.tool.AjaxResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "用户模块") 
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private UserService userService;

  @Autowired
  private TestUserService testUserService;

  // 在文档中介绍当前方法
  @ApiOperation("分页查询用户")
  /*
   * @ApiImplicitParam: 介绍单个参数
   * name属性: 介绍参数的名称
   * value属性: 介绍参数的值
   * dataType: 介绍参数的类型
   * required属性: 介绍此参数是否必须有值
   * paramType属性:请求数据的传递类型
   * header: 以请求头的方式传递到后端，后端使用@RequestHeader注解接收
   * query: 以key-value的方式传递到后端，后端使用@RequestParam注解接收
   * path: 以url路径的方式传递到后端，后端使用@PathVariable注解接收
   * body: 以json格式字符串的方式传递到后端，后端使用@RequestBody注解接收
   * 
   * @ApiImplicitParams: 介绍多个参数
   */
  @ApiImplicitParams({
      @ApiImplicitParam(name = "pageNum", value = "指定页码", dataType = "Integer", required = true, paramType = ""),
      @ApiImplicitParam(name = "pageSize", value = "指定每页记录数", dataType = "Integer", required = true)
  })
  @GetMapping("/getAllUserByPage")
  public PageInfo<User> getAllUser(Integer pageNum, Integer pageSize) {
    try {
      PageInfo<User> pageInfo = userService.findAllUserByPageHelper(pageNum, pageSize);
      return pageInfo;
    } catch (Exception e) {
      return null;
    }

  }

  @ApiOperation("添加用户")
  @PostMapping("/addUser")
  @SuppressWarnings("rawtypes")
  public Response addUser(User user) {
    try {

      System.out.println("获取的数据库连接为:" + dataSource.getConnection());

      userService.addUser(user);
      return AjaxResult.success("添加用户成功!");
    } catch (Exception e) {
      AjaxResult.fatal("上传单文件失败!", e);
      return null;
    }

  }

  @ApiOperation("用户检索")
  @PostMapping("/testUser")
  public TestUser testUser(@RequestBody TestUser testUser) {

    try {
      testUser = testUserService.TestUserSearch(testUser);
      return testUser;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

  @ApiOperation("用户List检索")
  @PostMapping("/testUserList")
  public List<TestUser> testUserList() {

    try {
      List<Integer> userAgeList = new ArrayList<>();
      userAgeList.add(18);
      userAgeList.add(20);
      List<TestUser> testUserList = testUserService.selectUserList(userAgeList);
      return testUserList;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

  @SuppressWarnings("rawtypes")
  @ApiOperation("增删改及事务test")
  @PostMapping("/testUserInsertUpdateDeleteTransactional")
  public Response testUserInsertUpdateDeleteTransactional(@RequestBody List<TestUser> testUserList) {
    try {
      int num = testUserService.testUserInsertUpdateDeleteTransactional(testUserList.get(0), testUserList.get(1), testUserList.get(2));
      if(num == 0) {
        return AjaxResult.success("失败!");
      }
      return AjaxResult.success("成功!");
    } catch (Exception e) {
      AjaxResult.fatal("失败!", e);
      return null;
    }
  }
}
