package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Article;
import java.util.List;

public interface ArticleDao {
    public List<Article> findAllArticles();
    public List<Article> findAllArticlesByUserid(int uid);
    public List<Article> findAllArticlesByColumnid(int cid);
}
