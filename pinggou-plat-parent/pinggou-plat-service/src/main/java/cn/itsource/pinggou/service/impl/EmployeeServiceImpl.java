package cn.itsource.pinggou.service.impl;

import cn.itosurce.pinggou.domain.Employee;
import cn.itsource.pinggou.mapper.EmployeeMapper;
import cn.itsource.pinggou.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zb
 * @version 1.0
 * @classname EmployeeServiceImpl
 * @description 员工Service接口实现类
 * @date 2019/5/14
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements IEmployeeService{
    @Override
    public Employee employeeLogin(String username) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        queryWrapper.eq("username", username);
        return this.baseMapper.selectOne(queryWrapper);
    }
}
