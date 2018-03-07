package com.puxintech.tywl.service.sys;

import com.puxintech.tywl.model.sys.Department;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

/**
 * @author yanhai
 */
public interface DepartmentService {

	Department findOne(int id);

	Page<Department> findByPage(String filter, PageRequest page);

	Department create(Department dept);

	Department update(Department dept);

	void remove(int id);

}
