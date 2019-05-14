package cn.itsource.pinggou.service;

import cn.itosurce.pinggou.domain.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zb
 * @version 1.0
 * @classname IEmployeeService
 * @description 员工Service接口
 * @date 2019/5/14
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * @author zb
     * @description 员工登录
     * @date 2019/5/14
     * @name employeeLogin
     * @param username
     * @return cn.itosurce.pinggou.domain.Employee
     */
    Employee employeeLogin(String username);
}
