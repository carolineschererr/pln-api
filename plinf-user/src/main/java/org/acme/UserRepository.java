package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    
    public User findByUsername(String username){
        return find("username", username).firstResult();
    }

    public User findByEmail(String email){
        return find("email", email).firstResult();
    }
    
}