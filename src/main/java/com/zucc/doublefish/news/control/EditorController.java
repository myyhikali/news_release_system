package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Result;
import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public Result save(HttpServletRequest request, HttpServletResponse response,@PathVariable("cid") int cid) throws ServletException, IOException {
        String title=request.getParameter("title");
        byte[] content=request.getParameter("content").getBytes();
        String state=request.getParameter("state");


        Cookie cookies[]= request.getCookies();
        String uid=null;
        Result rs = new Result();
        for(Cookie c:cookies){
            if(c.getName().equals("uid")){
                uid=c.getValue();
            }
        }
        if(title.equals("")||content.equals("")){
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","editor.html");
            rs.setStatus("title empty");
            System.out.println("empty");
            return rs;
        }
        else {
            Article article =new Article();
            article.setState(state);
            article.setContent(content);
            article.setTitle(title);
            article.setEid(Integer.parseInt(uid));
            article.setCid(cid);
            articleService.insertArticle(article);
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","editor.html");
            rs.setStatus("succeed");
        }
        return rs;
    }

    @RequestMapping("/{uid}/article")
    @ResponseBody
    public List<Article> findEditorArticles(@PathVariable("uid") int uid){
        return articleService.findAllArticlesByUserid(uid);
    }


}
