package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.listener.SessionListener;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.ArticleModify;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.pojo.Result;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    ColumnService columnService;
    @Autowired
    ArticleService articleService;

    @RequestMapping("/article")
    @ResponseBody
    public List<Article> findAllArticlesPublished(){
        return articleService.findAllArticlesPublished();
    }

    @RequestMapping("articlemodify/{aid}")
    @ResponseBody
    public List<ArticleModify> findAllArticleModifyByAid(HttpServletRequest request, HttpServletResponse response,@PathVariable("aid") int aid){
//        System.out.println("37:    "+aid);
        return articleService.findAllArticleModifiesByAid(aid);
    }

    @RequestMapping("/article/{aid}/{state}")
    @ResponseBody
    public Result allowPublish(HttpServletRequest request, HttpServletResponse response,@PathVariable("aid") int aid,@PathVariable("state") String state){
        Result rs = new Result();
//        uid,estate,aid
        int uid=-1;
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("SESSIONID")){
                uid = (Integer) SessionListener.sessionMap.get(cookie.getValue()).getAttribute("uid");
            }
        }

        if(uid==-1){
            rs.setStatus("failed");
            rs.setContent("尚未登录");
        }
        ArticleModify articleModify=new ArticleModify();
        articleModify.setUid(uid);
        articleModify.setEstate(state);
        articleModify.setAid(aid);
        articleService.insertArticleModify(articleModify);

        articleService.changeArticleStateByArticleid(aid,state);
        rs.setStatus("succeed");
        return rs;
    }

    @RequestMapping(value = "/modifycolumn",method = RequestMethod.PUT)
    @ResponseBody
    public Result modifyColumn(HttpServletRequest request, HttpServletResponse response){
        Result rs = new Result();
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cname = request.getParameter("cname");


        columnService.updateColumn(cid, cname);
        rs.setStatus("succeed");

        return rs;
    }

    @RequestMapping(value = "/addcolumn",method = RequestMethod.POST)
    @ResponseBody
    public Result addColumn(HttpServletRequest request, HttpServletResponse response){
        Result rs = new Result();
        String cname = request.getParameter("cname");
        int uid=-1;
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("SESSIONID")){
                uid = (Integer) SessionListener.sessionMap.get(cookie.getValue()).getAttribute("uid");
            }
        }

        if(uid==-1){
            rs.setStatus("failed");
            rs.setContent("尚未登录");
        }
        else if(columnService.findColumnByColumnName(cname)!=null){
            rs.setStatus("failed");
            rs.setContent("栏目已存在");
        }
        else{
            Column column = new Column();
            column.setCname(cname);
            column.setUid(uid);
            column.setCreatetime(new Date());
            columnService.insertColumn(column);
            rs.setStatus("succeed");
        }

        return rs;
    }

    @RequestMapping(value = "/deletecolumn",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteColumn(int cid){
        Result rs = new Result();
        Boolean state = columnService.deleteColumn(cid);
        if(state){
            rs.setStatus("succeed");
        }
        else{
            rs.setStatus("failed");
            rs.setContent("栏目中仍然存在文章,不能删除");
        }
        return rs;
    }
}
