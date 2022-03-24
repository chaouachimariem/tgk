package com.example.demo.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.AppRole;
import com.example.demo.Entity.AppUser;
import com.example.demo.Repository.AppRoleRepository;
import com.example.demo.Repository.AppUserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
       this.passwordEncoder = passwordEncoder;
    }

    /**
     * User Crud
     */
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) { return appUserRepository.findByUsername(username); }

    @Override
    public List<AppUser> listUser() { return appUserRepository.findAll(); }

    public AppUser getUserById(Long id){
        return appUserRepository.findById(id).orElse(null);
    }
    public AppUser getUserByFirstName(String firstname){
        return appUserRepository.findByFirstName(firstname);
    }

    public AppUser updateAppUser(AppUser appUser){
        AppUser existingAppUser= appUserRepository.findById(appUser.getId()).orElse(null);
        existingAppUser.setUsername (appUser.getUsername());
        existingAppUser.setPassword(appUser.getPassword());
        existingAppUser.setFirstName(appUser.getFirstName());
        existingAppUser.setAddress(appUser.getAddress());
        existingAppUser.setLastName(appUser.getLastName());
        existingAppUser.setEmail(appUser.getEmail());
        existingAppUser.setAppRoles(appUser.getAppRoles());
        return appUserRepository.save(existingAppUser);
    }
    public String deleteUser(Long id){
        appUserRepository.deleteById(id);
        return "User removed  !! "+id;
    }

    /**
     * Role Crud
     */
    @Override
    public AppRole addNewRole(AppRole appRole) { return appRoleRepository.save(appRole); }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    public AppRole updateAppRole(AppRole appRole){
        AppRole existingAppRole= appRoleRepository.findById(appRole.getId()).orElse(null);
        existingAppRole.setRoleName(appRole.getRoleName());
        return appRoleRepository.save(existingAppRole);
    }

    public String deleteRole(Long id){
        appRoleRepository.deleteById(id);
        return "Role removed  !! "+id;
    }

}
