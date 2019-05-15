package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.ColumnDao;
import com.zucc.doublefish.news.pojo.Column;
import com.zucc.doublefish.news.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    private ColumnDao columnDao;
    public List<Column> findAllColumns(){
        return this.columnDao.findAllColumns();
    }
    public void insertColumn(Column column){
        this.columnDao.insertColumn(column);
    }
    public void deleteColumn(int cid){
        this.columnDao.deleteColumn(cid);
    }
    public void updateColumn(Column column){
        this.columnDao.updateColumn(column);
    }
    public void updateColumn(int cid,String cname){
        Column column=this.columnDao.findColumnByColumnid(cid);
        column.setCname(cname);
        this.columnDao.updateColumn(column);
    }
    public Column findColumnByColumnid(int cid){
        return this.columnDao.findColumnByColumnid(cid);
    }
}
