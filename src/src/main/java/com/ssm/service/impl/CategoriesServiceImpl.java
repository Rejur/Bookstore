package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.CategoriesMapper;
import com.ssm.model.Books;
import com.ssm.model.Categories;
import com.ssm.model.Users;
import com.ssm.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoriesService")
public class CategoriesServiceImpl extends BaseService<Categories> implements CategoriesService{

    @Autowired
    CategoriesMapper categoriesMapper;
    public List<Categories> selectFPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        return selectAll();
    }

    public int insertnoid(Categories categories) {
        return categoriesMapper.insertnoid(categories);
    }
}
