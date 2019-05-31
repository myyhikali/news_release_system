import com.zucc.doublefish.news.pojo.*;
import com.zucc.doublefish.news.service.ArticleService;
import com.zucc.doublefish.news.service.ColumnService;
import com.zucc.doublefish.news.service.PictureService;
import com.zucc.doublefish.news.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringEscapeUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private PictureService pictureService;
    @Test
    public void test(){

        Article article=new Article();
        article.setContent("11111".getBytes());
        article.setEid(1);
        article.setState("save");
//        article.setAid(1);
        article.setTitle("1111111tile");
        article.setAid(232);
//        articleService.insertArticle(article);
        articleService.modifyArticleByArticleid(article);
    }
}
