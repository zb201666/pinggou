package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.util.AjaxResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zb
 * @version 1.0
 * @classname LoginController
 * @description 登录服务接口
 * @date 2019/5/11
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value="用户登录")
    public AjaxResult login(@RequestBody Map<String,Object> map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if(StringUtils.isEmpty(username)){
            return AjaxResult.me().setSuccess(false).setMessage("用户名为空");
        }
        if(StringUtils.isEmpty(password)){
            return AjaxResult.me().setSuccess(false).setMessage("密码为空");
        }
        if(!"zb".equals(username)){
            return AjaxResult.me().setSuccess(false).setMessage("用户名有误");
        }
        if(!"123456".equals(password)){
            return AjaxResult.me().setSuccess(false).setMessage("密码有误");
        }
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("username", username);
        user.put("password", password);
        return AjaxResult.me().setData(user);
    }
}
