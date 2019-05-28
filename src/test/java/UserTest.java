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
//        Article article=articleService.findArticleByArticleid(222);
//        System.out.println(article.getTitle());
//        System.out.println(article.getContent());
//        System.out.println(article.getContent().toString());
//        System.out.println(StringEscapeUtils.escapeHtml4(article.getContent().toString())
//        );
//        String s=" <h3>在此进行编辑你的文章</h3>";
//        byte[] bytes=s.getBytes();
//        System.out.println(bytes);
//        String s1=new String(bytes);
//        System.out.println(s1);
//        Picture picture = pictureService.findPicturesByAid(1);
//        System.out.println(picture.getPid());
//        Picture picture =new Picture();
//        picture.setAid(1);
//        picture.setPic("111124".getBytes());
//        pictureService.insertPicture(picture);
        List<Article> list=articleService.findAllArticlesByColumnid(1);
        for(Article article:list){
            System.out.println(article.getTime());
            System.out.println(article.getAid());
        }
        System.out.println();

    }
}
