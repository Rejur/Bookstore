package com.ssm.service;

import com.ssm.model.Books;

import java.util.List;

public interface BooksService extends IService<Books> {
    List<Books> selectByCountry(Books country, int page, int rows, String str);

    List<Books> selectByMult(Books country);

    List<Books> selectFPage(int page, int rows);

    int insertnoid(Books entity);
}
