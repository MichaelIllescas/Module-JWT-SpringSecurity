
package com.example.SpringSecurityJWT.repositories;

import com.example.SpringSecurityJWT.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ps2
 */
@Repository
public interface RoleRepository extends CrudRepository <RoleEntity, Long> {
    
    
}
