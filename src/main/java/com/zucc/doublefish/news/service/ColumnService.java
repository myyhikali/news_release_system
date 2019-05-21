package com.zucc.doublefish.news.service;

import com.zucc.doublefish.news.pojo.Column;

import java.util.List;

public interface ColumnService {
    public List<Column> findAllColumns();
    public void insertColumn(Column column);
    public boolean deleteColumn(int cid);
    public void updateColumn(int cid,String cname);
    public Column findColumnByColumnid(int cid);
    public Column findColumnByColumnName(String cname);
}
