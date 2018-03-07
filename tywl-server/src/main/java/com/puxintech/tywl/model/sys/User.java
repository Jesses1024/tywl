package com.puxintech.tywl.model.sys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private String gender;

	private List<Role> roles;

	private boolean isEnabled = true;

	private Date createdDate = new Date();

	private Date updatedDate = new Date();

	private Collection<GrantedAuthority> authorities;

	public User(String username, String password, String firstName, String lastName, String email, String phoneNumber,
			String gender, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.roles = roles;
	}

	public String getFullName() {
		if (StringUtils.hasText(firstName) && StringUtils.hasText(lastName)) {
			return firstName + lastName;
		}
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			authorities = new ArrayList<>();
			if (roles != null) {
				for (Role role : roles) {
					authorities.add(role);

					if (role.getPerms() != null) {
						authorities.addAll(role.getPerms());
					}
				}
			}
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
