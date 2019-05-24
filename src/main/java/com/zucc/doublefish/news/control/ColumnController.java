package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.dao.ColumnDao;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ColumnController {
    @Autowired
    private ColumnService columnService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value="/columns")
    @ResponseBody
    public List<Column> findAllColumns(){
        return columnService.findAllColumns();
    }

    @RequestMapping(value="/columns/{column}",method = RequestMethod.GET)
    @ResponseBody
    public List<Article> findArticleInColumn(@PathVariable("column") Integer columnId){
        return articleService.findAllArticlesByColumnid(columnId);
    }
}
