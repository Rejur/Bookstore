package com.ssm.mapper;

import com.ssm.model.Books;
import com.ssm.model.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value="booksMapper")
public interface BooksMapper extends Mapper<Books> {
    int insertnoid(Books books);
}