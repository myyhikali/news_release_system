package com.zucc.doublefish.news.service;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;
import com.zucc.doublefish.news.pojo.Picture;

public interface TransactionService {
    public void saveArticle(Article article, ArticleModify articleModify, Picture picture);
    public void modifyArticle(Article article,ArticleModify articleModify,Picture picture,boolean hasPicture);
    public void modifyArticleState(int aid,String state,ArticleModify articleModify);
}
