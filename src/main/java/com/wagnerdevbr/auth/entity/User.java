package com.wagnerdevbr.auth.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class User implements Serializable,UserDetails {

	private static final long serialVersionUID = 8466780495417005351L;

	@Id
	private Long id;

	@Column
	private String login;

	@Column
	private String name;
	
	@Column
	private String password;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private UserGroup userGroup;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userGroup.getModules();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
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

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}