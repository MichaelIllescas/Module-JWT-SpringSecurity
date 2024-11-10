
package com.example.SpringSecurityJWT.repositories;

import com.example.SpringSecurityJWT.models.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ps2
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
   
    Optional<UserEntity> findByUsername(String username);
    
}
