package com.beizhen.service;

public interface YcUserService {

    //人脸注册成功时将用户id保存至数据库
    Integer updUserByFaceId(Integer uid, String userFaceId);

}
