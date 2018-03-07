package com.puxintech.tywl.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puxintech.tywl.dao.sys.RoleDao;
import com.puxintech.tywl.model.exception.ServiceException;
import com.puxintech.tywl.model.sys.Role;
import com.puxintech.tywl.model.sys.RolePerm;
import com.puxintech.tywl.service.sys.RoleService;
import com.puxintech.tywl.util.Page;
import com.puxintech.tywl.util.PageRequest;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findOne(int id) {
		Role role = roleDao.findOne(id);
		role.setPerms(roleDao.getPerms(id));
		return role;
	}

	@Override
	public Page<Role> findByPage(String filter, PageRequest page) {
		List<Role> list = roleDao.findByPage(filter, page);
		int total = roleDao.count(filter);
		return new Page<>(list, total, page);
	}

	@Override
	public Role create(Role role) {
		int created = roleDao.create(role);
		if (created != 1) {
			throw new ServiceException("创建角色失败，请稍后再试");
		}

		addRolePerms(role.getId(), role.getPerms(), false);

		return findOne(role.getId());
	}

	@Override
	public Role update(Role role) {
		int updated = roleDao.update(role);
		if (updated != 1) {
			throw new ServiceException("创建角色失败，请稍后再试");
		}

		addRolePerms(role.getId(), role.getPerms(), true);

		return findOne(role.getId());
	}

	private void addRolePerms(int roleId, List<RolePerm> perms, boolean clear) {
		if (clear) {
			roleDao.removeRolePerms(roleId);
		}

		if (perms != null) {
			for (RolePerm rolePerm : perms) {
				roleDao.addRolePerm(roleId, rolePerm);
			}
		}
	}

	@Override
	public void remove(int id) {
		roleDao.remove(id);
	}

}
