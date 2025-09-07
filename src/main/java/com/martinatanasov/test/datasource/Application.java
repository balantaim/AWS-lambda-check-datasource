package com.martinatanasov.test.datasource;

import com.martinatanasov.test.datasource.user.UserRepository;
import com.martinatanasov.test.datasource.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.martinatanasov.test.datasource.user.UserController;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "org.example.controller")
@Import({ UserController.class, UserRepository.class, UserService.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}