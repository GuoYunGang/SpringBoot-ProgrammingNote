package com.gyg.controller;

import com.gyg.mapper.UserMapper;
import com.gyg.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/getEmps")
    public List<Employee> queryEmployee(){
        System.out.println("getEmps执行");
        return userMapper.queryEmployee();
    };

    @RequestMapping("/getEmp/{id}")
    public Employee queryEmployeeByID(@PathVariable("id") int id){
        return userMapper.queryEmployeeByID(id);
    };

    @RequestMapping("/addEmp")
    public int addEmployee(Employee employee){
        return userMapper.addEmployee(employee);
    };

    @RequestMapping("/updateEmp")
    public int updateEmployee(Employee employee){
        return userMapper.updateEmployee(employee);
    };

    @RequestMapping("/deleteEmp/{id}")
    public int deleteEmployee(@PathVariable("id") int id){
        System.out.println("deleteEmp执行");
        return userMapper.deleteEmployee(id);
    };
}
