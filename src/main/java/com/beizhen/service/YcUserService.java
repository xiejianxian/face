package com.beizhen.service;

import com.beizhen.entity.YcUser;

public interface YcUserService {

    //人脸注册成功时将用户id保存至数据库
    Integer updUserByFaceId(Integer uid, String userFaceId);

    //人脸识别成功后查询该用户
    YcUser findUserByFaceId(String userFaceId);

}
