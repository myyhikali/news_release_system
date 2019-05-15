import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import com.zucc.doublefish.news.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private ArticleService articleService;

    @Test
    public void test(){
//        User users = userService.findUserByUname("admin");
//        System.out.println(users.getUname());

//        List<Column> list=columnService.findAllColumns();
//        System.out.println(list);
//        List<Article> articleList=articleService.findAllArticlesByColumnid(1000);
//        System.out.println(articleList);
//        Article article=new Article();
//        article.setCid(210);
//        article.setEid(211);
//        article.setState(0);
//        articleService.insertArticle(article);

//        System.out.println(articleService.findArticleByArticleid(1).getAid());
//        articleService.deleteArticle(212);
//        int cid=1000;
//        String cname="马术";
//        columnService.updateColumn(cid, cname);
//        columnService.deleteColumn(1002);
        articleService.changeArticleStateByArticleid(1,"2");
    }
}
