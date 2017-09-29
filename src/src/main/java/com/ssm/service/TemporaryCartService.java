package com.ssm.service;

import com.ssm.model.Temporarycart;

public interface TemporaryCartService extends IService<Temporarycart> {
    int insertnoid(Temporarycart temporarycart);

    int delB(Temporarycart temporarycart);
}
