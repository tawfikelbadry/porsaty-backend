package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.model.TradingOrder;
import com.gradteam.porsaty.service.TradingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tawfik on 4/24/2018.
 */
@RestController
@RequestMapping("/api/tradingorders")
public class TradingOrdersResource {

    @Autowired
    private TradingOrderService tradingOrderService;


    @GetMapping("/all")
    public ResponseEntity showAllOrders(){
        return ResponseEntity.ok(tradingOrderService.getAllOrders());
    }


    @GetMapping("/{userId}")
    public ResponseEntity getUserOrderAsc(@PathVariable("userId")long userId){
        return ResponseEntity.ok(this.tradingOrderService.getUserOrdersDesc(userId));
    }

    // types : BUY | SELL
    @PostMapping("order")
    public ResponseEntity addOrder(@RequestBody TradingOrder tradingOrder){
        return ResponseEntity.ok(this.tradingOrderService.addOrder(tradingOrder));
    }





}
