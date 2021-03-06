package com.wagnerdevbr.auth.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class UserGroup implements Serializable {

	private static final long serialVersionUID = 8817220814799069707L;

	@Id
	@Column
	private Long id;
	@Column
	private String description;

	@OneToMany(mappedBy="users")
	private List<User> users;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="rights"
		, joinColumns={
			@JoinColumn(name="userGroup")
			}
		, inverseJoinColumns={
			@JoinColumn(name="module")
			}
		)
	private List<Module> modules;

	public UserGroup() {
	}

}