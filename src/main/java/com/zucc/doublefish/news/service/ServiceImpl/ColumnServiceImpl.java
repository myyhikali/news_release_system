package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.ArticleDao;
import com.zucc.doublefish.news.dao.ColumnDao;
import com.zucc.doublefish.news.pojo.Article;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    private ColumnDao columnDao;

    @Autowired
    private ArticleDao articleDao;

    public List<Column> findAllColumns(){
        return this.columnDao.findAllColumns();
    }

    @Transactional
    public void insertColumn(Column column){
        this.columnDao.insertColumn(column);
    }

    @Transactional
    public boolean deleteColumn(int cid){
        List<Article> articles = articleDao.findAllArticlesByColumnid(cid);
        if(articles==null || articles.size()!=0)
            return false;
        this.columnDao.deleteColumn(cid);
        return true;
    }
    public void updateColumn(Column column){
        this.columnDao.updateColumn(column);
    }
    @Transactional
    public void updateColumn(int cid,String cname){
        Column column=this.columnDao.findColumnByColumnid(cid);
        column.setCname(cname);
        this.columnDao.updateColumn(column);
    }
    public Column findColumnByColumnid(int cid){
        return this.columnDao.findColumnByColumnid(cid);
    }

    public Column findColumnByColumnName(String cname) { return this.columnDao.findColumnByColumnName(cname); }
}
