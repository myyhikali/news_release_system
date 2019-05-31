package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;

import java.util.List;

public interface ArticleDao {
    public List<Article> findAllArticles();
    public void modifyArticleByArticleid(Article article);
    public List<Article> findAllArticlesByUserid(int uid);
    public List<Article> findAllArticlesByColumnid(int cid);
    public List<Article> findArticlesWithState(String state);
    public List<Article> findArticlesExceptState(String state);
    public Article findArticleByArticleid(int aid);
    public void insertArticle(Article article);
    public void deleteArticle(int aid);
    public void changeArticleStateByArticleid(Article article);
    public void insertArticleModify(ArticleModify articleModify);
    public List<ArticleModify> findAllArticleModifiesByAid(int aid);
    public List<Article> findArticlesWithPictureByColumnid(int cid);
}
