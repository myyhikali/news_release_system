package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Column;

import java.util.List;

public interface ColumnDao {
    public List<Column> findAllColumns();
}
