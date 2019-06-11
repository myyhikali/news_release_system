package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;
import com.zucc.doublefish.news.pojo.Picture;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.PictureService;
import com.zucc.doublefish.news.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    ArticleService articleService;

    @Autowired
    PictureService pictureService;


    @Transactional
    public void saveArticle(Article article, ArticleModify articleModify, Picture picture) {

        articleService.insertArticle(article);
        picture.setAid(article.getAid());
        articleModify.setAid(article.getAid());
        articleService.insertArticleModify(articleModify);
        pictureService.insertPicture(picture);
    }

    @Transactional
    public void modifyArticle(Article article, ArticleModify articleModify, Picture picture,boolean hasPicture) {
        articleService.modifyArticleByArticleid(article);
        articleService.insertArticleModify(articleModify);

        if(hasPicture)
            pictureService.updatePicture(picture);
        else
            pictureService.insertPicture(picture);
    }

    @Transactional
    public void modifyArticleState(int aid, String state, ArticleModify articleModify) {
        articleService.insertArticleModify(articleModify);
        articleService.changeArticleStateByArticleid(aid,state);
    }
}
