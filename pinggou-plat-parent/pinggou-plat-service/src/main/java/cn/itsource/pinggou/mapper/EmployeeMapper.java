package cn.itsource.pinggou.mapper;

import cn.itosurce.pinggou.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zb
 * @version 1.0
 * @classname EmployeeMapper
 * @description 员工Mapper接口
 * @date 2019/5/14
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
