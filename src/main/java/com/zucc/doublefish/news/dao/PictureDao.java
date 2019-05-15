package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Picture;

import java.util.List;

public interface PictureDao {
    public List<Picture> findPicturesByAid(int aid);
}
