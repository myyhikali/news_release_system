package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.dao.ColumnDao;
import com.zucc.doublefish.news.pojo.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class ColumnController {
    @Autowired
    private ColumnDao columnDao;

    @RequestMapping(value="/columns")
    @ResponseBody
    public List<Column> findAllColumns(){
        return columnDao.findAllColumns();
    }
}
