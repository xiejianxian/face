package com.beizhen.service.impl;

import com.beizhen.entity.YcUser;
import com.beizhen.mapper.YcUserMapper;
import com.beizhen.service.YcUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class YcUserServiceImpl implements YcUserService {

    @Resource
    private YcUserMapper ycUserMapper;

    @Override
    public Integer updUserByFaceId(Integer uid, String userFaceId) {
        return ycUserMapper.updUserByFaceId(uid, userFaceId);
    }

    @Override
    public YcUser findUserByFaceId(String userFaceId) {
        return ycUserMapper.findUserByFaceId(userFaceId);
    }

}
