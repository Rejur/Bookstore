package com.ssm.mapper;

import com.ssm.model.Books;
import com.ssm.model.Categories;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value="categoriesMapper")
public interface CategoriesMapper extends Mapper<Categories> {
    int insertnoid(Categories categories);
}