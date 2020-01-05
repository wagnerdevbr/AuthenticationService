package com.wagnerdevbr.auth.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Module implements Serializable,GrantedAuthority {

	private static final long serialVersionUID = -6342351839593810074L;

	@Id
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToMany
	private List<UserGroup> userGroups;

	@Override
	public String getAuthority() {
		return getName();
	}

}