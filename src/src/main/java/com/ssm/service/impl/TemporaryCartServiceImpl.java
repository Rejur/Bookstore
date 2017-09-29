package com.ssm.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssm.mapper.TemporarycartMapper;
import com.ssm.model.Temporarycart;
import com.ssm.model.Users;
import com.ssm.service.TemporaryCartService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service("temporaryCartService")
public class TemporaryCartServiceImpl extends BaseService<Temporarycart> implements TemporaryCartService{

    @Resource
    TemporarycartMapper temporarycartMapper;

    public int insertnoid(Temporarycart temporarycart) {
        int flag = temporarycartMapper.insertnoid(temporarycart);
        return flag;
    }

    public int delB(Temporarycart temporarycart) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(temporarycart.getBookid());
        criteria.andEqualTo(temporarycart.getUserid());
        int flag = temporarycartMapper.deleteByExample(example);
        return flag;
    }

    public int del(Temporarycart temporarycart) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(temporarycart.getUserid());
        int flag = temporarycartMapper.deleteByExample(example);
        return flag;
    }
}
