package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Column;

import java.util.List;

public interface ColumnDao {
    public List<Column> findAllColumns();
    public Column findColumnByColumnid(int cid);
    public void insertColumn(Column column);
    public void deleteColumn(int cid);
    public void updateColumn(Column column);
}
