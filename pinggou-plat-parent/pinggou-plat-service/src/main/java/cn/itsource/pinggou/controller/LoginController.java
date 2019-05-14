package cn.itsource.pinggou.controller;

import cn.itosurce.pinggou.domain.Employee;
import cn.itsource.pinggou.service.IEmployeeService;
import cn.itsource.pinggou.util.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/login")
    @ApiOperation(value="用户登录")
    public AjaxResult login(@RequestBody Map<String,Object> map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        Employee employee = employeeService.employeeLogin(username);
        if (employee==null){
            return AjaxResult.me().setSuccess(false).setMessage("用户名有误！！！");
        }else{
            if(!employee.getPassword().equals(password)){
                return AjaxResult.me().setSuccess(false).setMessage("密码有误！！！");
            }else{
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("username", username);
                user.put("password", password);
                return AjaxResult.me().setData(user);
            }
        }
    }
}
