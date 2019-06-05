package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.Picture;

import java.util.List;

public interface PictureDao {
    public Picture findPicturesByAid(int aid);
    public void insertPicture(Picture picture);
    public void deletepictureByAid(int aid);
    public void updatePicture(Picture picture);
}
