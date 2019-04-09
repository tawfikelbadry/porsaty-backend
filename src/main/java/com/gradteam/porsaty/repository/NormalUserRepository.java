package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.NormalUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tawfik on 3/20/2018.
 */
@Repository
public interface NormalUserRepository extends CrudRepository<NormalUser,Long>{


    NormalUser findByUsername(String username);

    @Query("select n.image from NormalUser n where n.id=?1")
    byte [] getNormalUserImage(long imageId);

}
