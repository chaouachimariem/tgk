package com.example.demo;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.AppRole;
import com.example.demo.Entity.AppUser;
import com.example.demo.Service.AccountServiceImpl;



@SpringBootApplication
public class ProcessusLivraisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessusLivraisonApplication.class, args);
	}
	   @Bean
	    PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
	    
	    
	    
	    @Bean
		CommandLineRunner start( AccountServiceImpl  accountService) {
			
			return args->{
				
				accountService.addNewRole(new AppRole(null ,"USER"));
				accountService.addNewRole(new AppRole(null ,"ADMIN"));
				accountService.addNewRole(new AppRole(null ,"CUSTOMER_MANAGER"));
				accountService.addNewRole(new AppRole(null ,"PRODUCT_MANGER"));
				accountService.addNewRole(new AppRole(null ,"BILIS_MANGER"));
				
				accountService.addNewUser(new  AppUser(null,"admin","admin","admin@gmail.com","tunis","admin","admin" , new ArrayList<>()));
				
				accountService.addRoleToUser("admin", "USER");
				accountService.addRoleToUser("admin", "ADMIN");
			};
	}
}
