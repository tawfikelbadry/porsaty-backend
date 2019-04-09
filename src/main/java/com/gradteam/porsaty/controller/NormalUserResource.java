package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.model.NormalUser;
import com.gradteam.porsaty.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by tawfik on 3/20/2018.
 */
@RestController
@RequestMapping("/api/normal-users")
public class NormalUserResource {

    @Autowired
    private NormalUserService normalUserService;

    @GetMapping("")
    public List<NormalUser> getAllUsers(){
        return this.normalUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<NormalUser> getUser(@PathVariable("id") long id){
        return this.normalUserService.getUser(id);
    }


    @PostMapping("/register")
    public NormalUser saveUser(@RequestBody NormalUser user){
        return this.normalUserService.createNormalUser(user);
    }


    // show image of the Normal user by the normal user id
    @GetMapping(value = "/image/{userId}",produces = MediaType.IMAGE_JPEG_VALUE )
    public ResponseEntity showNormalUserImage(@PathVariable("userId") long userId){
        HttpHeaders headers = new HttpHeaders();
        byte[] media = this.normalUserService.getNormalUserImage(userId);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;

    }

}
