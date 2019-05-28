package com.zucc.doublefish.news.service;

import com.zucc.doublefish.news.pojo.Picture;

import java.util.List;

public interface PictureService {

    public Picture findPicturesByAid(int aid);
    public void insertPicture(Picture picture);
    public void deletepictureByAid(int aid);

}
