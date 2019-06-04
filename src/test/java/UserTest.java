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
import java.text.SimpleDateFormat;
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

        List<ArticleModify> list=articleService.findAllArticleModifiesByAid(228);
        for(ArticleModify articleModify:list){
            System.out.println(articleModify.getMtime());
            System.out.println(articleModify.getUname());
            System.out.println(articleModify.getEstate());
            System.out.println(articleModify.getTitle());

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(sdf.format(date));
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(articleModify.getMtime()));
        }
    }
}
