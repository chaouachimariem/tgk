package com.example.demo.Entity;




import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Entity


@Data
public class AppUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max =20)
    private String firstName;
    @NotBlank
    @Size(max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 50)
    private String address;
    @NotBlank
    @Size(max =50)
    private String username;
    @NotBlank
    @Size(max = 120)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> appRoles;
	
	
	
	
	
	
	
	
	
	public AppUser() {
		super();
	}
	public AppUser(Long id, @NotBlank @Size(max = 20) String firstName, @NotBlank @Size(max = 20) String lastName,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 50) String address,
			@NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 120) String password,
			List<AppRole> appRoles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;	
		this.email = email;
		this.address = address;
		this.username = username;
		this.password = password;
		this.appRoles = appRoles;
	}
	
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", username=" + username + ", password=" + password + ", appRoles="
				+ appRoles + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AppRole> getAppRoles() {
		return appRoles;
	}
	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

}
