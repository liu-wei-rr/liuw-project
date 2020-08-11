package com.liuw.management.controller.system;

import com.github.pagehelper.PageInfo;
import com.liuw.management.common.controller.BaseController;
import com.liuw.management.common.response.ResponseData;
import com.liuw.management.db.domain.system.User;
import com.liuw.management.db.domain.system.request.UserRequest;
import com.liuw.management.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuw
 * @date 2020-05-28 23:36
 */

@Api(value = "UserController", tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/id/{id}")
    public ResponseData getById(@PathVariable("id") Long id) {
        return success(userService.getById(id));
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/page")
    public ResponseData findByPage(@RequestBody UserRequest userRequest) {

        PageInfo<User> pageInfo = userService.findByPage(userRequest);

        return success(pageInfo.getList(), pageInfo.getTotal());
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    public ResponseData update(@RequestBody User user) {
        userService.update(user);
        return success();
    }

}
