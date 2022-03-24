package com.example.demo.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.JWTUtil;
import com.example.demo.Entity.AppRole;
import com.example.demo.Entity.AppUser;
import com.example.demo.Entity.RoleUserForm;
import com.example.demo.Service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class AccountController {
	 private AccountService accountService;


	    public AccountController(AccountService accountService) {
	        this.accountService = accountService;
	    }

	    @GetMapping(path = "/users")
	   // @PostAuthorize("hasAuthority('ADMIN')")
	    public List<AppUser> appUsers(){ return accountService.listUser();}

	    @PostMapping("/addUser")
	   //@PostAuthorize("hasAuthority('ADMIN')")
	    public AppUser saveUser(@RequestBody AppUser appUser){
	        return accountService.addNewUser(appUser);
	    }

	    @GetMapping("/findUserById/{id}")
	    public AppUser findUserById(@PathVariable Long id){
	        return accountService.getUserById(id);
	    }
	    @PutMapping("/updateUser")
	    public AppUser updateUser(@RequestBody AppUser appUser){
	        return accountService.updateAppUser(appUser);
	    }

	    @DeleteMapping("/deleteUser/{id}")
	    public String deleteUser(@PathVariable Long id){
	        return accountService.deleteUser(id);
	    }

	    @GetMapping("/findUserByFirstName/{firstname}")
	    public AppUser findUserByFirstName(@PathVariable String firstname){
	        return accountService.getUserByFirstName(firstname);
	    }

	    @PostMapping(path = "/addRole")
	    public AppRole saveRole(@RequestBody AppRole appRole){
	        return accountService.addNewRole(appRole);
	    }

	    @PutMapping("/updateRole")
	    public AppRole updateRole(@RequestBody AppRole appRole){return accountService.updateAppRole(appRole);}

	    @DeleteMapping("/deleteRole/{id}")
	    public String deleteRole(@PathVariable Long id){
	        return accountService.deleteRole(id);
	    }

	    @PostMapping(path="/addRoleToUser")
	    //@PostAuthorize("hasAuthority('ADMIN')")
	    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
	        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
	    }

	    @GetMapping(path="/refreshToken")
	    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
	        String authToken=request.getHeader(JWTUtil.AUTH_HEADER);
	        if(authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
	            try{
	                String jwt=authToken.substring(JWTUtil.PREFIX.length());
	                Algorithm algorithm=Algorithm.HMAC256(JWTUtil.SECRET);
	                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
	                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
	                String username=decodedJWT.getSubject();
	                AppUser appUser=accountService.loadUserByUsername(username);
	                String jwtAccessToken= JWT.create()
	                        .withSubject(appUser.getUsername())
	                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
	                        .withIssuer(request.getRequestURL().toString())
	                        .withClaim("role",appUser.getAppRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
	                        .sign(algorithm);
	                Map<String,String> idToken=new HashMap<>();
	                idToken.put("access-token",jwtAccessToken);
	                idToken.put("refresh-token",jwt);
	                response.setContentType("application/json");
	                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
	            }
	            catch(Exception e){
	                throw e;
	            }
	        }
	        else{
	            throw new RuntimeException("Refresh Token Required  !!!");
	        }
	    }
	    @GetMapping(path = "/profile")
	    public AppUser profile(Principal principal){
	        return accountService.loadUserByUsername(principal.getName());
	    }
}
