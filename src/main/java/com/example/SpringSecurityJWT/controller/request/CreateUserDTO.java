
package com.example.SpringSecurityJWT.controller.request;

/**
 *
 * @author ps2
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ps2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    
    @Email
    @NotBlank
    private String email;
       
    @NotBlank
    private String username;

    @NotBlank    
    private String password;
    
    private Set<String> roles;
    
}

