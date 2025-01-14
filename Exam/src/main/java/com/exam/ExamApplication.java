 package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.Exception.UserNotFoundException;
import com.exam.Exception.UserFoundException;
import com.exam.Models.Role;
import com.exam.Models.User;
import com.exam.Models.UserRole;
import com.exam.Servises.UserService;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Application Starting ...................");
		try
		{
			User user = new User();
			
			user.setFirstName("Silva");
			user.setLastName("Samal");
			user.setUserName("silva2000");
			user.setPassword(this.bCryptPasswordEncoder.encode("pratap"));
			user.setEmail("silva9@gamail.com");
			user.setPhone("9937975507");
			user.setProfile("default.png");
			
			Role role = new Role();
			role.setRoleId(44L);
			role.setRoleName("ADMIN");
			
			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			
			userRole.setRole(role);
			userRole.setUser(user);
			userRoleSet.add(userRole);
			
			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());
		}
		catch (UserFoundException e) {
			e.getStackTrace();
		}
		
		
		
	}

}
