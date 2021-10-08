package com.gyg.mapper;

import com.gyg.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义员工的接口
 */
@Mapper
@Repository
public interface EmployeeMapper {

    List<Employee> queryAllEmp();

    Employee queryEmpById(int id);

    int addEmp(Employee employee);

    int updateEmp(Employee employee);

    int deleteEmpById(int id);

}
