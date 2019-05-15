package com.zucc.doublefish.news.service;

import com.zucc.doublefish.news.pojo.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> findAllArticles();
    public List<Article> findAllArticlesByUserid(int uid);
    public List<Article> findAllArticlesByColumnid(int cid);
    public Article findArticleByArticleid(int aid);
    public void insertArticle(Article article);
    public void deleteArticle(int aid);
    public void changeArticleStateByArticleid(int aid,String state);
}
