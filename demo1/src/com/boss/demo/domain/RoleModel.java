package com.boss.demo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * RoleModel entity. @author MyEclipse Persistence Tools
 */

public class RoleModel implements Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String description;
	private Set<FunctionModel> functions = new HashSet(0);
	private Set<UserModel> users = new HashSet(0);

	// Constructors

	/** default constructor */
	public RoleModel() {
	}

	/** minimal constructor */
	public RoleModel(String id) {
		this.id = id;
	}

	/** full constructor */
	public RoleModel(String id, String name, String code, String description,
					 Set functions, Set users) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.functions = functions;
		this.users = users;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set functions) {
		this.functions = functions;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "RoleModel{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", functions=" + functions +
				", users=" + users +
				'}';
	}
}