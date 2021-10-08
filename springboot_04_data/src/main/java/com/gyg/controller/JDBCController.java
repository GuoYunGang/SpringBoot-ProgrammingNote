package com.gyg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 郭运刚
 */
@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询数据库的所有信息
     */
    @GetMapping("/emp")
    public List<Map<String, Object>> userList() {
        String sql = "select * from t_employee";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println("查询结果：" + mapList);
        return mapList;
    }

    /**
     * 增
     */
    @GetMapping("/addEmp")
    public String addEmp(){
        String sql = "INSERT INTO t_employee(empName,gender,email) VALUES('大王',1,'dawang@qq.com')";
        int update = jdbcTemplate.update(sql);
        return "add-OK";
    }


    /**
     * 删
     */
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") int id){
        String sql = "delete from t_employee where id=?";
        int update = jdbcTemplate.update(sql,id);
        return "delete-OK";
    }

    /**
     * 改
     */
    @GetMapping("/updateEmp/{id}")
    public String updateEmp(@PathVariable("id") int id){
        String sql = "update t_employee set empName=?,email=? where id=" + id;
        Object[] objects = new Object[2];
        objects[0] = "张飒";
        objects[1] = "666666@qq.com";
        int update = jdbcTemplate.update(sql,objects);
        return "update-OK";
    }

}
