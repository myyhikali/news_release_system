package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.listener.SessionListener;
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

    public Result save(HttpServletRequest request, HttpServletResponse response,@PathVariable("cid") int cid,@RequestBody Result contentJson) throws ServletException, IOException {

        String title=request.getParameter("title");

        System.out.println(contentJson.getContent());
        byte[] content=contentJson.getContent().getBytes();
        System.out.println(contentJson.getContent().getBytes());
        String state=request.getParameter("state");


        Cookie cookies[]= request.getCookies();
        HttpSession session;
        int uid=-1;
        Result rs = new Result();
        for(Cookie c:cookies){
            if(c.getName().equals("SESSIONID")){
                session = SessionListener.sessionMap.get(c.getValue());
                uid = (Integer)session.getAttribute("uid");
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
            article.setEid(uid);
            article.setCid(cid);
            articleService.insertArticle(article);
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","editor.html");
            rs.setStatus("succeed");
        }
        return rs;
    }

    @RequestMapping("/article")
    @ResponseBody
    public List<Article> findEditorArticles(HttpServletRequest request, HttpServletResponse response){
        int uid = -1;
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("SESSIONID")){
                uid = (Integer)SessionListener.sessionMap.get(cookie.getValue()).getAttribute("uid");
            }
        }
        System.out.println("uid"+uid);
        return articleService.findAllArticlesByUserid(uid);
    }


    @RequestMapping("/article/{aid}/{state}")
    @ResponseBody
    public Result allowPublish(HttpServletRequest request, HttpServletResponse response,@PathVariable("aid") int aid,@PathVariable("state") String state){
        Result rs = new Result();

        articleService.changeArticleStateByArticleid(aid,state);
        rs.setStatus("succeed");
        return rs;
    }
}



