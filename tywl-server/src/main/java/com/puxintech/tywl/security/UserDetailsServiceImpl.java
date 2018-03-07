package com.puxintech.tywl.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.puxintech.tywl.dao.sys.RoleDao;
import com.puxintech.tywl.dao.sys.UserDao;
import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findOne(username);
		List<Role> roles = userDao.getRoles(username);

		roles.stream().forEach(r -> r.setPerms(roleDao.getPerms(r.getId())));

		user.setRoles(roles);

		return user;
	}

}
