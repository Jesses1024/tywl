package com.puxintech.tywl.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.RolePerm;
import com.puxintech.tywl.util.PageRequest;

@Mapper
public interface RoleDao {

	@Select("select * from role where id = #{id}")
	Role findOne(@Param("id") int id);

	List<Role> findByPage(@Param("filter") String filter, @Param("page") PageRequest page);

	int count(@Param("filter") String filter);

	int create(Role role);

	@Update("update role set name=#{name}, deptId=#{deptId}, description=#{description} where id = #{id}")
	int update(Role role);

	@Delete("delete from role where id = #{id}")
	int remove(@Param("id") int id);

	@Select("select perm,data_scope from role_perm where role_id = #{roleId}")
	List<RolePerm> getPerms(@Param("roleId") int roleId);

	@Insert("insert into role_perm(role_id, perm, data_scope) values(#{roleId}, #{perm.perm}, #{perm.dataScope})")
	int addRolePerm(@Param("roleId") int roleId, @Param("perm") RolePerm perm);

	@Delete("delete from role_perm where role_id = #{roleId}")
	int removeRolePerms(@Param("roleId") int roleId);
}
