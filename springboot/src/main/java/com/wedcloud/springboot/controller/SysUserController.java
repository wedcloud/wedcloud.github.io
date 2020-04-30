package com.wedcloud.springboot.controller;

import com.wedcloud.springboot.mapper.SysUserMapper;
import com.wedcloud.springboot.pojo.SysUser;
import com.wedcloud.springboot.util.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>用户
 *
 * @author : 许先生
 * @date : 2020-04-24 15:16
 */
@RestController
@RequestMapping("/v1")
public class SysUserController {

  @Resource private SysUserMapper mapper;

  @GetMapping("/user")
  @ApiOperation("用户列表查询")
  public ResponseEntity<ResponseBean<List<SysUser>>> getUser() {
    List<SysUser> userList = mapper.findByAll();
    return ResponseEntity.ok(ResponseBean.ok(userList));
  }
}
