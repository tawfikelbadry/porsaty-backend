package com.gradteam.porsaty.service;

import com.gradteam.porsaty.config.SecurityUtility;
import com.gradteam.porsaty.model.NormalUser;
import com.gradteam.porsaty.model.security.Role;
import com.gradteam.porsaty.model.security.UserRole;
import com.gradteam.porsaty.repository.NormalUserRepository;
import com.gradteam.porsaty.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by tawfik on 3/20/2018.
 */
@Service
public class NormalUserService {

    private static final Logger LOG= LoggerFactory.getLogger(UserSecurityService.class);


    private NormalUserRepository normalUserRepository;
    private RoleRepository roleRepository;

    @Autowired
    public NormalUserService(NormalUserRepository normalUserRepository, RoleRepository roleRepository) {
        this.normalUserRepository = normalUserRepository;
        this.roleRepository = roleRepository;
    }

    public List<NormalUser> getAllUsers(){
        return (List<NormalUser>)this.normalUserRepository.findAll();
    }

    public Optional<NormalUser> getUser(long id){
        return this.normalUserRepository.findById(id);
    }

    public NormalUser saveUser(NormalUser user){
        return this.normalUserRepository.save(user);
    }

    // Register new Normal User
    public NormalUser createNormalUser(NormalUser user){

        NormalUser localUser=normalUserRepository.findByUsername(user.getUsername());
        if(null!=localUser){
            LOG.warn("this username {} already exist , no thing happen",user.getUsername());
        }else {
            // get Normal user Role
            Role roleUser = roleRepository.findByName("ROLE_USER");

            // add Normal user Role to the new User
            Set<UserRole> userRoleSet=new HashSet<>();
            userRoleSet.add(new UserRole(user,roleUser));
            user.setUserRoles(userRoleSet);

//            user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));

            // save the user
            localUser=this.normalUserRepository.save(user);

        }

        return localUser;
    }


    public byte[] getNormalUserImage(long normalUserId){
        return this.normalUserRepository.getNormalUserImage(normalUserId);
    }

}
