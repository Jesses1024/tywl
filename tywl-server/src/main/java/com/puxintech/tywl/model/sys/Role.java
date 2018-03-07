package com.puxintech.tywl.model.sys;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private static final String defaultRolePrefix = "ROLE_";

	private Integer id;

	private String name;

	private Integer deptId;

	private String description;

	private List<RolePerm> perms;

	@Override
	public String getAuthority() {
		return defaultRolePrefix + name;
	}
}
