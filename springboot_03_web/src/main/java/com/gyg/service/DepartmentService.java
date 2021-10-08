package com.gyg.service;

import com.gyg.mapper.DepartmentMapper;
import com.gyg.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门的业务逻辑层
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 获取到所有的部门信息
     * @return
     */
    public List<Department> queryAllDepart(){
        return departmentMapper.queryAllDepart();
    };
}
