package com.gyg.service;

import com.gyg.mapper.EmployeeMapper;
import com.gyg.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工的业务逻辑层
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 获取到所有的员工信息
     * @return
     */
    public List<Employee> queryAllEmp(){
        return employeeMapper.queryAllEmp();
    };

    /**
     * 根据id查找指定员工
     * @param id
     * @return
     */
    public Employee queryEmpById(int id){
        return employeeMapper.queryEmpById(id);
    };

    /**
     * 添加员工
     * @param employee
     * @return
     */
    public int addEmp(Employee employee){
        return employeeMapper.addEmp(employee);
    };

    /**
     * 修改员工
     * @param employee
     * @return
     */
    public int updateEmp(Employee employee){
        return employeeMapper.updateEmp(employee);
    };

    /**
     * 根据ID删除员工
     * @param id
     * @return
     */
    public int deleteEmpById(int id){
        return employeeMapper.deleteEmpById(id);
    };
}
