package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;
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


    public List<Article> findAllArticlesPublished() {
        return articleDao.findArticlesWithState("checked");
    }

    public List<Article> findArticlesWithPictureByColumnid(int cid) {
        return articleDao.findArticlesWithPictureByColumnid(cid);
    }

    public void insertArticle(Article article){
        this.articleDao.insertArticle(article);
    }
    public Article findArticleByArticleid(int aid){
        return this.articleDao.findArticleByArticleid(aid);
    }
    public void deleteArticle(int aid){
        this.articleDao.deleteArticle(aid);
    }
    public void changeArticleStateByArticleid(int aid,String state){
        Article article=this.findArticleByArticleid(aid);
        article.setState(state);
        this.articleDao.changeArticleStateByArticleid(article);
    }

    public void insertArticleModify(ArticleModify articleModify){
        articleDao.insertArticleModify(articleModify);
    }

    public List<ArticleModify> findAllArticleModifiesByAid(int aid){
        return articleDao.findAllArticleModifiesByAid(aid);
    }
}
