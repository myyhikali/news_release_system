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
//        List<Article> list=articleService.findAllArticlesByColumnid(1);
//        for(Article article:list){
//            System.out.println(article.getTime());
//            System.out.println(article.getAid());
//        }
//        System.out.println();
        ArticleModify articleModify=new ArticleModify();
        articleModify.setUid(1);
        articleModify.setEstate("upload");
        articleModify.setAid(12);
//        articleModify.setMtime(new Date(System.currentTimeMillis()));

        articleService.insertArticleModify(articleModify);

//        List<ArticleModify> articleModify=articleService.findAllArticleModifiesByAid(12);
//        for (ArticleModify articleModify1:articleModify){
//            System.out.println(articleModify1.getMtime());
//            System.out.println(articleModify1.getUname());
//        }
    }
}
