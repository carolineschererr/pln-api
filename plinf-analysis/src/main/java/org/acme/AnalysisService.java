package org.acme;

import java.util.List;
import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@ApplicationScoped
public class AnalysisService {
    
    @Inject
    AnalysisRepository analysisRepository;
    
    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @Transactional
    @RolesAllowed("User")
    public List<Analysis> getAllAnalysis() {
        try {
            return analysisRepository.listAll().isEmpty() ? null : analysisRepository.listAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage()); //colocar essa estrutura abaixo
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public Analysis getAnalysisById(long id) {
        try {
            if(Objects.isNull(analysisRepository.findById(id))) {
                throw new Exception("Analysis not found."); //implementar da classe Exception
            }
            return analysisRepository.findById(id);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public Analysis createAnalysis(Analysis analysis) {
        try {
            analysisRepository.getEntityManager().merge(analysis);
            return analysis;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    @RolesAllowed({ "User" })
    public Boolean deleteAnalysis(long id) {
        try{
            Analysis analysisTemp = analysisRepository.findById(id);
            if(Objects.equals(analysisTemp, null)){
            throw new Exception("Analysis not found.");
            }

            analysisRepository.delete(analysisTemp);
            return true;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}