package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Picture;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import com.zucc.doublefish.news.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value="/read/{read}",method = RequestMethod.GET)
    @ResponseBody
    public Article readArticle(@PathVariable("read") int aid){
        Article article=articleService.findArticleByArticleid(aid);
        article.setContent1(new String(article.getContent()));
        return article;
    }

    @RequestMapping(value="/picture/{aid}",method = RequestMethod.GET)
    public void readPicture(@PathVariable("aid") int aid, HttpServletResponse response){
        Picture picture = pictureService.findPicturesByAid(aid);
        try {
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            os.write(picture.getPic());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
