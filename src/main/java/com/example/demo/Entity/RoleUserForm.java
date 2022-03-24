package com.example.demo.Entity;

import javax.persistence.Entity;

import lombok.Data;

@Data

public class RoleUserForm {
	
	private String username;
    private String roleName;
	
	
	
	
	
	 public RoleUserForm(String username, String roleName) {
			super();
			this.username = username;
			this.roleName = roleName;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		@Override
		public String toString() {
			return "RoleUserForm [username=" + username + ", roleName=" + roleName + ", getUsername()=" + getUsername()
					+ ", getRoleName()=" + getRoleName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
		public RoleUserForm() {
			super();
		}
}
