package com.puxintech.tywl.dao.sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.puxintech.tywl.model.sys.Department;
import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.RolePerm;
import com.puxintech.tywl.util.PageRequest;

@RunWith(SpringRunner.class)
@MybatisTest
public class RoleDaoTests {

	@Autowired
	private RoleDao dao;

	@Autowired
	private DepartmentDao deptDao;

	@Test
	public void findOneShouldIsNull() throws Exception {
		Role result = dao.findOne(1);
		assertNull(result);
	}

	@Test
	public void findByPageShouldBeEmptyList() throws Exception {
		List<Role> result = dao.findByPage(null, new PageRequest(0, 10, null, true));
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	public void createAndAddRolePermShouldNonError() throws Exception {
		int created, updated;
		Department department;
		Role role;

		department = new Department(null, "test", "test", null);
		created = deptDao.create(department);
		assertEquals(1, created);

		role = new Role(null, "test", department.getId(), null, null);
		created = dao.create(role);
		assertEquals(1, created);
		assertNotNull(role.getId());

		created = dao.addRolePerm(role.getId(), new RolePerm("ROLE_ADMIN", "1,2,3"));
		assertEquals(1, created);

		assertEquals(1, dao.getPerms(role.getId()).size());

		updated = dao.removeRolePerms(role.getId());
		assertEquals(1, updated);

		dao.remove(role.getId());
		deptDao.remove(department.getId());
	}

	@Test
	public void addRolePermShouldThrowForeignException() throws Exception {
		try {
			dao.addRolePerm(1, new RolePerm("user:view", "1,2,3"));
		} catch (Exception e) {
			assertTrue(e.getMessage().toLowerCase().contains("fk_role_perm_role_id"));
		}
	}
}
