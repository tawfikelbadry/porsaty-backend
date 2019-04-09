package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tawfik on 4/27/2018.
 */
@Repository
public interface NewsRepository extends CrudRepository<News,Long> {

    List<News> findByOrderByDateAsc();

    List<News> findByCompanyIdOrderByDateAsc(long companyId);

    @Query("select n.image from News n where n.id=?1")
    byte [] getNewsImage(long newsId);
}
