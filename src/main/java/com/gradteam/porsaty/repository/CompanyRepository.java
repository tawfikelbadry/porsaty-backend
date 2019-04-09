package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long>{

    List<Company> findAllByCompanySectorId(int id);

    Company findByCompanyName(String companyName);

    Company findByEmail(String email);

    Company findByStockId(long stockId);


    @Query("select c.image from Company c where c.id=?1")
    byte [] getCompanyImage(long imageId);


    List<Company> findFirst4ByOrderByTradingVolumeDesc();

    List<Company> findByOrderByTradingVolumeDesc(Pageable pageable);

    List<Company> findByCompanySectorIdOrderByTradingVolumeDesc(int sector_id,Pageable pageable);



}
