package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AnalysisRepository implements PanacheRepository<Analysis>{
    
    public Analysis findByUsername(String username){
        return find("username", username).firstResult();
    }
    
}