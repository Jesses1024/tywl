package com.puxintech.tywl.model.sys;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePerm implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private String perm;

	private String dataScope;

	public Set<String> getScopesAsList() {
		return StringUtils.commaDelimitedListToSet(dataScope);
	}

	@Override
	public String getAuthority() {
		return perm;
	}
}
