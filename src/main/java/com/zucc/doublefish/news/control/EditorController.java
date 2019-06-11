package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.listener.SessionListener;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;
import com.zucc.doublefish.news.pojo.Picture;
import com.zucc.doublefish.news.pojo.Result;
import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.PictureService;
import com.zucc.doublefish.news.service.TransactionService;
import com.zucc.doublefish.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public Result save(HttpServletRequest request, HttpServletResponse response,@PathVariable("cid") int cid,@RequestBody Result contentJson) throws ServletException, IOException {

        ArticleModify articleModify = new ArticleModify();

        String title=request.getParameter("title");
        String filename = request.getParameter("filename");

        FileInputStream file = null;
        byte[] bytes = new byte[0];
        try {
            file = new FileInputStream(new File(request.getSession().getServletContext().getRealPath("/")+"temp/"+filename));
            bytes  = new byte[file.available()];
            file.read(bytes);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picture picture = new Picture();
        picture.setPname(filename);
        picture.setPic(bytes);

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

        Article article =new Article();
        article.setState(state);
        article.setContent(content);
        article.setTitle(title);
        article.setEid(uid);
        article.setCid(cid);
        response.setHeader("REDIRECT","REDIRECT");
        response.setHeader("CONTEXTPATH","editor.html");
        rs.setStatus("succeed");

        articleModify.setEstate(state);
        articleModify.setUid(uid);
        transactionService.saveArticle(article,articleModify,picture);
        return rs;
    }

    @RequestMapping("/modify/{cid}/{aid}")
    @ResponseBody
    public Result modify(HttpServletRequest request, HttpServletResponse response,@PathVariable("cid") int cid,@PathVariable("aid") int aid,@RequestBody Result contentJson) throws ServletException, IOException {

        ArticleModify articleModify = new ArticleModify();
        Article article = articleService.findArticleByArticleid(aid);
        Boolean hasPicture = false;
        String title=request.getParameter("title");
        String filename = request.getParameter("filename");

        FileInputStream file = null;
        byte[] bytes = new byte[0];
        try {
            file = new FileInputStream(new File(request.getSession().getServletContext().getRealPath("/")+"temp/"+filename));
            bytes  = new byte[file.available()];
            file.read(bytes);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picture picture = pictureService.findPicturesByAid(aid);
        if(picture==null)
            picture = new Picture();
        else
            hasPicture = true;
        picture.setPname(filename);
        picture.setPic(bytes);
        picture.setAid(aid);

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
        article.setState(state);
        article.setContent(content);
        article.setTitle(title);
        article.setEid(uid);
        article.setCid(cid);

        response.setHeader("REDIRECT","REDIRECT");
        response.setHeader("CONTEXTPATH","editor.html");
        rs.setStatus("succeed");

        articleModify.setAid(aid);
        articleModify.setEstate(state);
        articleModify.setUid(uid);

        transactionService.modifyArticle(article,articleModify,picture,hasPicture);
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
//        System.out.println("uid"+uid);
        List<Article> list=articleService.findAllArticlesByUserid(uid);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getState().equals("deleted")){
                list.remove(i);
                i--;
            }
        }
        return list;
    }


    @RequestMapping("/article/{aid}/{state}")
    @ResponseBody
    public Result allowPublish(HttpServletRequest request, HttpServletResponse response,@PathVariable("aid") int aid,@PathVariable("state") String state){
        Result rs = new Result();

        int uid = -1;
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("SESSIONID")){
                uid = (Integer)SessionListener.sessionMap.get(cookie.getValue()).getAttribute("uid");
            }
        }
        ArticleModify articleModify=new ArticleModify();
        articleModify.setAid(aid);
        articleModify.setEstate(state);
        articleModify.setUid(uid);
        transactionService.modifyArticleState(aid,state,articleModify);
        rs.setStatus("succeed");
        return rs;
    }

    @RequestMapping("/savedarticle/{aid}")
    @ResponseBody
    public Article savedarticle(HttpServletRequest request, HttpServletResponse response,@PathVariable("aid") int aid){
        Article article=articleService.findArticleByArticleid(aid);
        article.setContent1(new String(article.getContent()));
        return article;
    }


        @RequestMapping(value = "/uploadpicture",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Result uploadPicture(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="file",required=false) MultipartFile uploadFile){

        Result rs = new Result();
        if(!uploadFile.isEmpty())
        {
            System.out.println(uploadFile.getOriginalFilename());
            System.out.println(request.getSession().getServletContext().getRealPath("/"));
            File newFile = new File(request.getSession().getServletContext().getRealPath("/")+"temp/"+uploadFile.getOriginalFilename());
            try {
                uploadFile.transferTo(newFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return rs;
    }


}



