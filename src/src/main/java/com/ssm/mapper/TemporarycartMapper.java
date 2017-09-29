package com.ssm.mapper;

import com.ssm.model.Temporarycart;
import com.ssm.model.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value="temporarycartMapper")
public interface TemporarycartMapper extends Mapper<Temporarycart> {
    int insertnoid(Temporarycart temporarycart);

}