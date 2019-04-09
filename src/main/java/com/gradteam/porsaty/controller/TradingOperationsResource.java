package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.service.TradingOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tawfik on 5/1/2018.
 */
@RestController
@RequestMapping("/api/trading-operations")
public class TradingOperationsResource {

    @Autowired
    private TradingOperationService tradingOperationService;

    // return all user trading operation sorted desc by date by passing {user id}
    @GetMapping("/{userId}")
    public ResponseEntity getUserTradingOperationsDesc(@PathVariable("userId") long userId){
        return ResponseEntity.ok(tradingOperationService.getUserTradingOperationsDesc(userId));
    }

    // return all stock trading operation sorted desc by date by passing {stock id}
    @GetMapping("/stock/{stockId}")
    public ResponseEntity getStockTradingOperationsDesc(@PathVariable("stockId") long stockId){
        return ResponseEntity.ok(tradingOperationService.getStockTradingOperationsDesc(stockId));
    }

}
