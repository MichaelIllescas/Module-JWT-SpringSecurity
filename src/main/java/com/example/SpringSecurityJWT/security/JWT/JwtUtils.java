
package com.example.SpringSecurityJWT.security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ps2
 */
@Component
@Slf4j
public class JwtUtils {
    
    @Value("${jwt.secret.key}")
    private String secretKey;
    
    @Value("${jwt.time.expiration}")
    private String timeExpiration;
    
    //Crear un token de acceso
    
    public String generateAccesTken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong( timeExpiration)  ))
                .signWith(getSignatureKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    
    //obtener firma del token
    
    public Key getSignatureKey(){
        byte[]keybytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keybytes);
    }
    
    // Validar el token de acceso
    
    public boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("Token Invalido. error: ".concat(e.getMessage()));
            return false;
        }
        
    }
    
    
    //obtener el username del token
    
    public String getUsernameFromToken(String token ){
    
        return getClaim(token, Claims::getSubject);
    }

    //obtener un solo claims
    public <T> T getClaim(String token, Function <Claims, T > claimsTFunctions){
        Claims claims= extractoAllClaims(token);
        return claimsTFunctions.apply(claims);
    }
    
    //obtener todos los claims del token
     public Claims extractoAllClaims(String token){
     
     return  Jwts.parser()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
     }
    
    
    
    
}
