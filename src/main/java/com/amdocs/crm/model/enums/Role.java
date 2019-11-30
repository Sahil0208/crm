package com.amdocs.crm.model.enums;

public enum Role {
	
	ROLE_SUPER_ADMIN(1, "Role Super Admin"), ROLE_ADMIN(2, "Role Admin"), ROLE_USER(3, "Role User");
	
	/** The role id. */
	private int roleId;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new role.
	 *
	 * @param roleId the role id
	 * @param name the name
	 */
	private Role(int roleId, String name) {
		this.roleId = roleId;
		this.name = name;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
