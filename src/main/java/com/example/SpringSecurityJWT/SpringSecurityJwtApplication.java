package com.example.SpringSecurityJWT;

import com.example.SpringSecurityJWT.models.ERole;
import com.example.SpringSecurityJWT.models.RoleEntity;
import com.example.SpringSecurityJWT.models.UserEntity;
import com.example.SpringSecurityJWT.repositories.UserRepository;
import java.util.Collections;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.SpringSecurityJWT"})
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);

    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

//    @Bean
//    CommandLineRunner init() {
//
//        return args -> {
//            UserEntity userEntity = UserEntity.builder()
//                    .email("admin@gmail.com")
//                    .username("admin")
//                    .password(passwordEncoder.encode("1234"))
//                    .roles(Collections.singleton(
//                            RoleEntity.builder()
//                                    .name(ERole.ADMIN)
//                                    .build()
//                    ))
//                    .build();
//
//        userRepository.save(userEntity);
//    };

//}
}