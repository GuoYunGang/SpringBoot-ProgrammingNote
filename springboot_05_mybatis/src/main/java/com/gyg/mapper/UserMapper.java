package com.gyg.mapper;

import com.gyg.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis的mapper类
 */
@Mapper
@Repository
public interface UserMapper {

    List<Employee> queryEmployee();

    Employee queryEmployeeByID(int id);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int id);

}
