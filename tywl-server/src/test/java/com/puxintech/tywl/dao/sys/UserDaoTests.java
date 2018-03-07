package com.puxintech.tywl.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.puxintech.tywl.model.sys.User;
import com.puxintech.tywl.util.PageRequest;

@RunWith(SpringRunner.class)
@MybatisTest
public class UserDaoTests {

	@Autowired
	private UserDao dao;

	@Test
	public void findByUsernameShouldBeNull() throws Exception {
		User result = dao.findOne("admin");
		assertNull(result);
	}

	@Test
	public void findByPageShouldBeEmptyList() throws Exception {
		List<User> result = dao.findByPage(null, new PageRequest(0, 10, "first_name", true));
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	public void createUserShouldBeSuccess() throws Exception {
		int created = dao.create(new User("admin", "admin", null, null, null, null, null, null));
		assertEquals(1, created);
	}
}
