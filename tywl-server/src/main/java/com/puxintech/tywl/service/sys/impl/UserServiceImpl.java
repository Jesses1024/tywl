package com.puxintech.tywl.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puxintech.tywl.dao.sys.UserDao;
import com.puxintech.tywl.model.exception.ServiceException;
import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.User;
import com.puxintech.tywl.service.sys.UserService;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public User findOne(String username) {
		User user = userDao.findOne(username);
		user.setRoles(userDao.getRoles(username));
		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<User> findByPage(String filter, PageRequest page) {
		List<User> list = userDao.findByPage(filter, page);
		int total = userDao.count(filter);
		return new Page<>(list, total, page);
	}

	@Override
	public User create(User user) {
		int created = userDao.create(user);

		if (created != 1) {
			throw new ServiceException("用户创建失败，请稍后再试");
		}

		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				userDao.addRole(user.getUsername(), role.getId());
			}
		}

		return findOne(user.getUsername());
	}

	@Override
	public User update(User user) {
		int updated = userDao.update(user);
		if (updated != 1) {
			throw new ServiceException("用户更新失败，请稍后再试");
		}

		userDao.removeRoles(user.getUsername());

		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				userDao.addRole(user.getUsername(), role.getId());
			}
		}

		return findOne(user.getUsername());
	}

	@Override
	public void remove(String username) {
		userDao.remove(username);
	}

}
