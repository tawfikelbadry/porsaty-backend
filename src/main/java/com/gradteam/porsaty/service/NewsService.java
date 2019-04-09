package com.gradteam.porsaty.service;

import com.gradteam.porsaty.model.Company;
import com.gradteam.porsaty.model.News;
import com.gradteam.porsaty.repository.CompanyRepository;
import com.gradteam.porsaty.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by tawfik on 4/27/2018.
 */
@Service
public class NewsService {


    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public byte[] getNewsImageWithId(long newsId){
        return this.newsRepository.getNewsImage(newsId);
    }

    public News getOneNewById(long newsId)throws Exception{
        return this.newsRepository.findById(newsId).get();
    }

    public List<News> getAllNewsOrderd(){
        return newsRepository.findByOrderByDateAsc();
    }

    public long addNew(News news){
        return this.newsRepository.save(news).getId();
    }

    public long addNews(long companyId,News news){
        Company company=companyRepository.findById(companyId).get();
        company.getNewsSet().add(news);
        companyRepository.save(company);
        return company.getId();
    }

    public List<News> getCompanyNewsOrderd(long companyId){
        return this.newsRepository.findByCompanyIdOrderByDateAsc(companyId);
    }

    public long updateNews(News news){
        if(newsRepository.findById(news.getId())==null){
            return -1;
        }
        return newsRepository.save(news).getId();
    }

    public void deleteNews(long newsId){
         this.newsRepository.deleteById(newsId);
    }

}
