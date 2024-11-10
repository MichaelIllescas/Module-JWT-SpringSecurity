
/**
 *
 * @author ps2
 */
package com.example.SpringSecurityJWT.controller;

import com.example.SpringSecurityJWT.controller.request.CreateUserDTO;
import com.example.SpringSecurityJWT.models.ERole;
import com.example.SpringSecurityJWT.models.RoleEntity;
import com.example.SpringSecurityJWT.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringSecurityJWT.repositories.UserRepository;

@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepositry;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/Hello")
    public String hello(){
        return "Hello word not secure";
    }
    
    
     @GetMapping("/HelloSecured")
    public String helloSecured(){
        return "Hello word secured";
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<?>createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        
        Set<RoleEntity> roles= createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                        .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
               .username(createUserDTO.getUsername())
                 .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                 
               
                .roles(roles)
                .build();
        
        userRepositry.save(userEntity);
        
        return ResponseEntity.ok(userEntity);
    
    }
    
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
    
     userRepositry.deleteById(Long.parseLong(id));
    
     return "Se ha borrado user con id " + id;
    }
    
    
}
