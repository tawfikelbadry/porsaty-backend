package com.gradteam.porsaty.controller;

import com.gradteam.porsaty.model.News;
import com.gradteam.porsaty.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by tawfik on 4/27/2018.
 */
@RestController
@RequestMapping("/api/news")
public class NewsResource {

    @Autowired
    private NewsService newsService;

    @GetMapping("")
    public ResponseEntity showNewsOrderd(){
        return ResponseEntity.ok(newsService.getAllNewsOrderd());
    }

    @GetMapping("/{id}")
    public ResponseEntity showOneNews(@PathVariable("newsId")long id){
        try {
            return ResponseEntity.ok(this.newsService.getOneNewById(id));
        } catch (Exception e) {
            return new ResponseEntity("this news not available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity showCompanyNewsOrderd(@PathVariable long id){
        return ResponseEntity.ok(newsService.getCompanyNewsOrderd(id));
    }




    @PostMapping("/add/{companyId}")
    public ResponseEntity addNew(@PathVariable("companyId")long companyId, @RequestBody News news){
        long compid=this.newsService.addNews(companyId,news);
        if(compid>0){

            return ResponseEntity.created(URI.create("/api/news/"+companyId)).build();
        }
        return ResponseEntity.badRequest().body("news not saved successfully");
    }


    @PutMapping("/update")
    public ResponseEntity updateNew(@RequestBody News news){
        this.newsService.updateNews(news);
        return ResponseEntity.ok(news);
    }


    @DeleteMapping("/remove/{newsId}")
    public ResponseEntity deleteNews(@PathVariable("newsId")long newsId){
        this.newsService.deleteNews(newsId);
        return ResponseEntity.ok("news with Id "+newsId+" has removed");
    }


    // show image of the News by the news id
    @GetMapping(value = "/image/{newsId}",produces = MediaType.IMAGE_JPEG_VALUE )
    public ResponseEntity showNewsImage(@PathVariable("newsId") long newsId){
        HttpHeaders headers = new HttpHeaders();
        byte[] media = this.newsService.getNewsImageWithId(newsId);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;

    }

}
