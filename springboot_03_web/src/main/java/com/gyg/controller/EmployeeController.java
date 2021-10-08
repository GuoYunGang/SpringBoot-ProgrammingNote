package com.gyg.controller;


import com.gyg.pojo.Department;
import com.gyg.pojo.Employee;
import com.gyg.service.DepartmentService;
import com.gyg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 员工管理
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 获取所有员工，并返回到显示页
     *
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    public String queryAllEmp(Model model) {
        List<Employee> employees = employeeService.queryAllEmp();
        model.addAttribute("emps", employees);
        System.out.println(employees);
        return "emp/list";
    }

    /**
     * 跳转到添加员工页面
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddPage(Model model) {
        List<Department> departments = departmentService.queryAllDepart();
        System.out.println("查询结果:");
        System.out.println(departments);
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /**
     * 添加员工
     *
     * @return
     */
    @PostMapping("/emp")
    public String addEmployee(Employee employee) {
        System.out.println("save-------------  " + employee);
        Integer did = employee.getDepartment().getId();
        employee.setDid(did);

        //添加员工
        employeeService.addEmp(employee);

        //重定向到列表页面
        return "redirect:/emps";
    }

    /**
     * 去员工的修改页面
     *
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        //获取到该员工信息
        Employee employee = employeeService.queryEmpById(id);
        model.addAttribute("emp", employee);
        System.out.println("empID:" + employee);
        //获取到所有的部门信息
        Collection<Department> departments = departmentService.queryAllDepart();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    /**
     * 修改员工
     * @return
     */
    @PostMapping("updateEmp")
    public String updateEmp(Employee employee){
        Integer did = employee.getDepartment().getId();
        employee.setDid(did);
        employeeService.updateEmp(employee);
        System.out.println("修改数据：" + employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmpById(id);
        System.out.println("删除成功！" + id);
        return "redirect:/emps";
    }
}
