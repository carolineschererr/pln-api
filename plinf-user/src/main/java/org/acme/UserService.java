package org.acme;

import java.util.List;
import java.util.Objects;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@ApplicationScoped
public class UserService {
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @Transactional
    @RolesAllowed("User")
    public List<User> getAllUsers() {
        try{
            return userRepository.listAll().isEmpty() ? null : userRepository.listAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage()); //colocar essa estrutura abaixo
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public User getUserById(long id) {
        try {
            if(Objects.isNull(userRepository.findById(id))) {
                throw new Exception("User not found."); //implementar da classe Exception
            }
            return userRepository.findById(id);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public User getUserByUsername(String username) {
        try{
            if(Objects.isNull(username)) {
                throw new Exception("User not found."); //implementar da classe Exception
            }
            return userRepository.findByUsername(username);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public User getUserByEmail(String email) {
        try{
            if(Objects.isNull(email)) {
                throw new Exception("User not found.");
            }
            return userRepository.findByEmail(email);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @PermitAll
    public User createUser(User user) {
        try{
            if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
                throw new Exception("The e-mail informed is currently being used.");
            } else {
                userRepository.getEntityManager().merge(user);
            }
            
            return user;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @PermitAll
    public User login(User user) {
        try{
            User userFound = userRepository.findByEmail(user.getEmail());
            if (Objects.isNull(userFound)) {
                throw new Exception("E-mail not registered.");
            }
            if (!userFound.getPassword().equals(user.getPassword())) {
                throw new Exception("Wrong password.");
            }
            return userFound;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    @RolesAllowed({ "User" })
    public User updateUser(User user) {
        try {
            User userTemp = userRepository.findById(user.getId());
            if(Objects.equals(userTemp, null)){
            throw new Exception("User not found.");
            }

            userTemp.setUsername(user.getUsername());
            userTemp.setEmail(user.getEmail());
            userTemp.setPassword(user.getPassword());
            userTemp.setFirstName(user.getFirstName());
            userTemp.setLastName(user.getLastName());
            userTemp.setBirthDate(user.getBirthDate());
            userTemp.setCountry(user.getCountry());
            
            userRepository.persist(userTemp);
            return userTemp;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @RolesAllowed({ "User" })
    public Boolean deleteUser(long id) {
        try{
            User userTemp = userRepository.findById(id);
            if(Objects.equals(userTemp, null)){
            throw new Exception("User not found.");
            }

            userRepository.delete(userTemp);
            return true;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}