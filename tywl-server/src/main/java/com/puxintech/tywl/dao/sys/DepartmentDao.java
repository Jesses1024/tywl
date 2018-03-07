package com.puxintech.tywl.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.puxintech.tywl.model.sys.Department;
import com.puxintech.tywl.util.PageRequest;

@Mapper
public interface DepartmentDao {

	@Select("select * from department where id = #{id}")
	Department findOne(@Param("id") int id);

	List<Department> findAll(@Param("filter") String filter);

	List<Department> findByPage(@Param("filter") String filter, @Param("page") PageRequest page);

	int count(@Param("filter") String filter);

	int create(Department department);

	@Update("update department set name=#{name}, type=#{type}, description=#{description} where id = #{id}")
	int update(Department department);

	@Delete("delete from department where id = #{id}")
	int remove(@Param("id") Integer id);
}
