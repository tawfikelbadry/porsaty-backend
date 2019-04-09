package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.model.UserStocks;
import com.gradteam.porsaty.service.UserStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tawfik on 3/22/2018.
 */
@RestController
@RequestMapping("/api/userstocks")
public class UserStocksResource {

    @Autowired
    UserStocksService userStocksService;

    @GetMapping("/all")
    public List<UserStocks> getAllUserStocks(){
        return this.userStocksService.getAllUserStocks();
    }

    @GetMapping("/{stockId}")
    public ResponseEntity getStockUsersWithQuantity(@PathVariable long stockId){
        return ResponseEntity.ok(this.userStocksService.getStockUsers(stockId));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity getUserStocksByUserId(@PathVariable long userId){
        return ResponseEntity.ok( this.userStocksService.getAllUserStocksByUserId(userId));
    }
}
