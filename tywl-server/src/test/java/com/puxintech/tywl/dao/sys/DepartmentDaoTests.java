package com.puxintech.tywl.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.puxintech.tywl.model.sys.Department;
import com.puxintech.tywl.util.PageRequest;

@RunWith(SpringRunner.class)
@MybatisTest
public class DepartmentDaoTests {

	@Autowired
	private DepartmentDao dao;

	@Test
	public void createShouldBeSetIdProperty() throws Exception {
		Department department = new Department(null, "test", "test", null);
		int created = dao.create(department);
		assertEquals(1, created);
		assertNotNull(department.getId());
	}

	@Test
	public void findOneShouldBeNull() throws Exception {
		Department result = dao.findOne(1);
		assertEquals(null, result);
	}

	@Test
	public void findAllShouldBeEmpty() throws Exception {
		List<Department> result = dao.findAll(null);
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	public void findByPageShouldBeEmpty() throws Exception {
		List<Department> result = dao.findByPage(null, new PageRequest(0, 10, null, true));
		assertEquals(Collections.emptyList(), result);
	}
}
