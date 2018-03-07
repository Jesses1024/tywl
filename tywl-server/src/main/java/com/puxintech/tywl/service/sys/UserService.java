package com.puxintech.tywl.service.sys;

import com.puxintech.tywl.model.sys.User;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

/**
 * @author yanhai
 */
public interface UserService {

	User findOne(String username);

	Page<User> findByPage(String filter, PageRequest page);

	User create(User user);

	User update(User user);

	void remove(String username);
}
