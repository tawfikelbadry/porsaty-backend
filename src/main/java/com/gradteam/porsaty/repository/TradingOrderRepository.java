package com.gradteam.porsaty.repository;

import com.gradteam.porsaty.model.TradingOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tawfik on 4/24/2018.
 */
@Repository
public interface TradingOrderRepository extends CrudRepository<TradingOrder,Long> {

    List<TradingOrder> findByUserIdOrderByDateTimeDesc(long userId);
}
