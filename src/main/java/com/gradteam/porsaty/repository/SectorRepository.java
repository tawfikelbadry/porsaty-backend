package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.MarketSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends CrudRepository<MarketSector,Integer>{

    MarketSector findById(int id);

}
