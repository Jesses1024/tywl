package com.puxintech.tywl.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puxintech.tywl.dao.sys.DepartmentDao;
import com.puxintech.tywl.model.exception.ServiceException;
import com.puxintech.tywl.model.sys.Department;
import com.puxintech.tywl.service.sys.DepartmentService;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public Department findOne(int id) {
		return departmentDao.findOne(id);
	}

	@Override
	public Page<Department> findByPage(String filter, PageRequest page) {
		List<Department> list = departmentDao.findByPage(filter, page);
		int total = departmentDao.count(filter);
		return new Page<>(list, total, page);
	}

	@Override
	public Department create(Department dept) {
		int created = departmentDao.create(dept);
		if (created != 1) {
			throw new ServiceException("创建部门失败，请稍后再试");
		}
		return findOne(dept.getId());
	}

	@Override
	public Department update(Department dept) {
		int updated = departmentDao.update(dept);
		if (updated != 1) {
			throw new ServiceException("更新部门失败, 请稍后再试");
		}
		return findOne(dept.getId());
	}

	@Override
	public void remove(int id) {
		departmentDao.remove(id);
	}

}
