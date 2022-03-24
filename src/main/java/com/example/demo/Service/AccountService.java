package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.AppRole;
import com.example.demo.Entity.AppUser;


public interface AccountService {
	  AppUser addNewUser(AppUser appUser);
	    AppUser loadUserByUsername (String username);
	    List<AppUser> listUser();
	    AppUser getUserById(Long id);
	    AppUser updateAppUser(AppUser appUser);
	    String deleteUser(Long id);
	    AppUser getUserByFirstName(String firstname);


	    void addRoleToUser(String username, String roleName);


	    AppRole addNewRole(AppRole appRole);
	    AppRole updateAppRole(AppRole appRole);
	    String deleteRole(Long id);

}
