package com.example.project;

import com.example.project.dao.UserDao;
import com.example.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProjectApplication {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@PostConstruct
	public void init() {
		dao.save(new User("admin", "admin","admin",
				passwordEncoder.encode("admin")));
		
	}

}
