package com.gradteam.porsaty;

import com.gradteam.porsaty.model.NormalUser;
import com.gradteam.porsaty.model.User;
import com.gradteam.porsaty.model.security.Role;
import com.gradteam.porsaty.model.security.UserRole;
import com.gradteam.porsaty.service.DataBinder;
import com.gradteam.porsaty.service.NormalUserService;
import com.gradteam.porsaty.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class PorsatyApplication{


	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(PorsatyApplication.class, args);
	}


}
