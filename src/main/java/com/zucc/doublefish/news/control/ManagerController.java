package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.pojo.Result;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/article")
    @ResponseBody
    public List<Article> findAllArticlesPublished(){
        return null;
    }

    @RequestMapping(value = "/modifycolumn",method = RequestMethod.PUT)
    @ResponseBody
    public Result modifyColumn(HttpServletRequest request, HttpServletResponse response){
        Result rs = new Result();
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cname = request.getParameter("cname");

        columnService.updateColumn(cid,cname);
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
            if(cookie.getName().equals("uid")){
                uid = Integer.parseInt(cookie.getValue());
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
