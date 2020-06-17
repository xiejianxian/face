package com.beizhen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface YcUserMapper {

    //人脸注册成功时将用户id保存至数据库
    Integer updUserByFaceId(@Param("uid")Integer uid, @Param("userFaceId")String userFaceId);

}