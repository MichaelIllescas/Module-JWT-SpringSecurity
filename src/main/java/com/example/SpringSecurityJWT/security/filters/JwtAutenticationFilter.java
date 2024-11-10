
package com.example.SpringSecurityJWT.security.filters;

import com.example.SpringSecurityJWT.models.UserEntity;
import com.example.SpringSecurityJWT.security.JWT.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author ps2
 */
public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter{

   
    private JwtUtils jwutils;
    
    public JwtAutenticationFilter(JwtUtils jwutils){
        this.jwutils=jwutils;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
                                                HttpServletResponse response) throws AuthenticationException 
    {
        UserEntity userEntity= null;
        String username="";
        String password="";
        
        try {
            userEntity=new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username=userEntity.getUsername();
            username=userEntity.getPassword();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        UsernamePasswordAuthenticationToken autenticationToken= new UsernamePasswordAuthenticationToken(username, password);
                
        
        return  getAuthenticationManager().authenticate(autenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        
        User user=(User) authResult.getPrincipal();
        String token = jwutils.generateAccesTken(user.getUsername());
        
        response.addHeader("Authorization", token);
        Map<String, Object> httpResponse= new HashMap<>();
        httpResponse.put("Token",token);
        httpResponse.put("Message","Autenticacion correcta");
        httpResponse.put("Username",user.getUsername());
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
