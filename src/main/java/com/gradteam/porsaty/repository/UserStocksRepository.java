package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.UserStocks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tawfik on 3/22/2018.
 */
@Repository
public interface UserStocksRepository  extends CrudRepository<UserStocks,Long>{

    List<UserStocks> findByStockId(long stock);

    @Query("select s.quantity,s.stock.id,s.stock.company.companyName,s.stock.latestPrice,s.paidPrice from UserStocks s where s.user.id=?1")
    List<Object[]> findUserStocksByUserId(long userId);
}
