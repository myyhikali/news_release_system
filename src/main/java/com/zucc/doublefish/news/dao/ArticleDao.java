package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Article;
import java.util.List;

public interface ArticleDao {
    public List<Article> findAllArticles();
    public List<Article> findAllArticlesByUserid(int uid);
    public List<Article> findAllArticlesByColumnid(int cid);
<<<<<<< HEAD
=======
    public List<Article> findArticlesWithState(int state);
    public List<Article> findArticlesExceptState(int state);
>>>>>>> f073f34f676406a26e69802f52a6de32c8943130
    public Article findArticleByArticleid(int aid);
    public void insertArticle(Article article);
    public void deleteArticle(int aid);
    public void changeArticleStateByArticleid(Article article);
}
