package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    private ColumnService columnService;

    @RequestMapping(value="/read/{read}",method = RequestMethod.GET)
    @ResponseBody
    public Article readArticle(@PathVariable("read") int aid){
        Article article=articleService.findArticleByArticleid(aid);
        article.setContent1(new String(article.getContent()));
        return article;
    }
}
