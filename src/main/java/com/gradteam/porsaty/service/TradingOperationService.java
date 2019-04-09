package com.gradteam.porsaty.service;

import com.gradteam.porsaty.model.TradingOperation;
import com.gradteam.porsaty.repository.TradingOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tawfik on 5/1/2018.
 */
@Service
public class TradingOperationService {

    @Autowired
    TradingOperationRepository tradingOperationRepository;

    public List<TradingOperation> getUserTradingOperationsDesc(long userId){
        return this.tradingOperationRepository.findByUserIdOrderByDateDesc(userId);
    }


    public List<TradingOperation> getStockTradingOperationsDesc(long stockId) {
        return this.tradingOperationRepository.findByStockIdOrderByDateDesc(stockId);
    }
}
