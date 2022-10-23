package com.springboot.blog.blog;

import com.springboot.blog.blog.Configuration.AppConstants;
import com.springboot.blog.blog.entities.Role;
import com.springboot.blog.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("BCrypted Password : " + passwordEncoder.encode("123"));
		try{
			Role roleAdmin = new Role();
			roleAdmin.setId(AppConstants.ADMIN_USER);
			roleAdmin.setName("ROLE_ADMIN");
			roleRepo.save(roleAdmin);

			Role roleNormal = new Role();
			roleNormal.setId(AppConstants.NORMAL_USER);
			roleNormal.setName("ROLE_NORMAL");
			roleRepo.save(roleNormal);

		} catch (Exception e){

		}
	}
}
