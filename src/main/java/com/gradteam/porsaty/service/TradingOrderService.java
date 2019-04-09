package com.gradteam.porsaty.service;

import com.gradteam.porsaty.model.TradingOrder;
import com.gradteam.porsaty.repository.TradingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tawfik on 4/24/2018.
 */
@Service
public class TradingOrderService {

    @Autowired
    private TradingOrderRepository tradingOrderRepository;


    public Iterable<TradingOrder> getAllOrders(){
        return  this.tradingOrderRepository.findAll();
    }

    public TradingOrder addOrder(TradingOrder tradingOrder){
        return this.tradingOrderRepository.save(tradingOrder);
    }


    public List<TradingOrder> getUserOrdersDesc(long userId) {
        return this.tradingOrderRepository.findByUserIdOrderByDateTimeDesc(userId);
    }
}
