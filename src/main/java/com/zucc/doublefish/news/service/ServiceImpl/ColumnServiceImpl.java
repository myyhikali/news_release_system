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
}
