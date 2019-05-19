package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagerController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/article")
    @ResponseBody
    public List<Article> findAllArticlesPublished(){
        return null;
    }
}
