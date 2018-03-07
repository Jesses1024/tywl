package com.puxintech.tywl.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.User;
import com.puxintech.tywl.util.PageRequest;

@Mapper
public interface UserDao {

	@Select("select * from `user` where username = #{username}")
	User findOne(@Param("username") String username);

	List<User> findByPage(@Param("filter") String filter, @Param("page") PageRequest page);

	int count(@Param("filter") String filter);

	int create(User user);

	@Update("update `user` set first_name=#{firstName}, last_name=#{lastName}, email=#{email}, phone_number=#{phoneNumber}, gender=#{gender}, is_enabled=#{isEnabled}, updated_date=current_timestamp where username = #{username}")
	int update(User user);

	@Delete("delete from `user` where id = #{username}")
	int remove(@Param("username") String username);

	@Select("select r.* from role r,user_role ur where r.id = ur.role_id and ur.user_username = #{username}")
	List<Role> getRoles(@Param("username") String username);

	@Insert("insert into user_role(user_username, role_id) values(#{username}, #{roleId})")
	int addRole(@Param("username") String username, @Param("roleId") int roleId);

	@Delete("delete from user_role where user_username = #{username}")
	int removeRoles(@Param("username") String username);
}
