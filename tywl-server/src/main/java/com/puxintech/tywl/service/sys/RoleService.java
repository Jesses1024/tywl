package com.puxintech.tywl.service.sys;

import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

/**
 * @author yanhai
 */
public interface RoleService {

	Role findOne(int id);

	Page<Role> findByPage(String filter, PageRequest page);

	Role create(Role role);

	Role update(Role role);

	void remove(int id);
}
