package com.ssm.service;

import com.ssm.model.Categories;

import java.util.List;

public interface CategoriesService extends IService<Categories> {
    List<Categories> selectFPage(int page, int rows);

    int insertnoid(Categories categories);
}
