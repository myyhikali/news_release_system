package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    public List<Article> findAllArticles(){
        return this.articleDao.findAllArticles();
    }
    public List<Article> findAllArticlesByUserid(int uid){
        return this.articleDao.findAllArticlesByUserid(uid);
    }
    public List<Article> findAllArticlesByColumnid(int cid){
        return this.articleDao.findAllArticlesByColumnid(cid);
    }
}
