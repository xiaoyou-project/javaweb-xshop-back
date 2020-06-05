package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.PicDao;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.PicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PicServiceImpl implements PicService {

    @Autowired
    private PicDao picDao;

    // 上传图片
    @Override
    public Results add(String uuid, int userID) {
        picDao.add(uuid, userID);
        return new Results(1, "上传成功");
    }

}
