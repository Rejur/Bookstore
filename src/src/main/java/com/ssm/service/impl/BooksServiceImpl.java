package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.BooksMapper;
import com.ssm.model.Books;
import com.ssm.model.Users;
import com.ssm.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service("booksService")
public class BooksServiceImpl extends BaseService<Books> implements BooksService {
    @Autowired
    BooksMapper booksMapper;

    @Override
    public List<Books> selectByCountry(Books books, int page, int rows, String str) {
        Example example = new Example(Books.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(books.getTitle())) {
            criteria.andLike("title", "%" + books.getTitle() + "%");
        }
        if(books.getCategoryid() != null) {
            criteria.andEqualTo("categoryid", books.getCategoryid());
        }
        if (books.getId() != null) {
            criteria.andEqualTo("id", books.getId());
        }
        example.setOrderByClause(str + " asc");
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    public List<Books> selectByMult(Books books) {
        Example example = new Example(Books.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(books.getTitle())) {
            criteria.andLike("title", "%" + books.getTitle() + "%");
        }
        if(books.getCategoryid() != null) {
            criteria.andEqualTo("categoryid", books.getCategoryid());
        }
        if (books.getId() != null) {
            criteria.andEqualTo("id", books.getId());
        }
        return selectByExample(example);
    }

    public List<Books> selectFPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        return selectAll();
    }

    public int insertnoid(Books entity) {
        return booksMapper.insertnoid(entity);
    }
}
