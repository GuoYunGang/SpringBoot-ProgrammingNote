package com.gyg.mapper;

import com.gyg.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义部门的接口
 */
@Mapper
@Repository
public interface DepartmentMapper {
    List<Department> queryAllDepart();
}
