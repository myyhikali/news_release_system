import com.zucc.doublefish.news.pojo.*;
import com.zucc.doublefish.news.service.*;
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
    @Autowired
    private TransactionService transactionService;
    @Test
    public void test(){
        articleService.findAllArticlesPublished();
        ArticleModify articleModify = new ArticleModify();
        articleModify.setAid(0);
        articleModify.setMid(0);
        articleModify.setEstate("published");
        transactionService.modifyArticleState(-1,"published",articleModify);
    }
}
