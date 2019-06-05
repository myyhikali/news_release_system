package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.PictureDao;
import com.zucc.doublefish.news.pojo.Picture;
import com.zucc.doublefish.news.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    public Picture findPicturesByAid(int aid){

        return pictureDao.findPicturesByAid(aid);
    }
    public void insertPicture(Picture picture){
        pictureDao.insertPicture(picture);
    }
    public void deletepictureByAid(int aid){
        pictureDao.deletepictureByAid(aid);
    }

    public void updatePicture(Picture picture) {
        pictureDao.updatePicture(picture);
    }


}
