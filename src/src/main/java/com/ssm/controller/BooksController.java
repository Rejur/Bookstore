package com.ssm.controller;

import com.github.pagehelper.Page;
import com.ssm.controller.common.BaseController;
import com.ssm.model.Books;
import com.ssm.model.Categories;
import com.ssm.model.Publishers;
import com.ssm.model.Users;
import com.ssm.service.BooksService;
import com.ssm.service.PublishersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.utils.PageBean;
import com.ssm.utils.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.print.Book;
import java.util.List;

@Controller
@Scope(value="prototype")
@RequestMapping("Books")
public class BooksController extends BaseController{

    @Resource
    BooksService booksService;

    @Resource
    CategoriesServiceImpl categoriesService;

    @Resource
    PublishersService publishersService;

    @RequestMapping(value="Detail", method= RequestMethod.GET)
    public ModelAndView getDetail(HttpServletRequest request) {
        Integer key = Integer.valueOf(request.getParameter("id"));
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        Books books = booksService.selectByKey(key);
        Categories categories = categoriesService.selectByKey(books.getCategoryid());
        Publishers publishers = publishersService.selectByKey(books.getPublisherid());
        mv.addObject("cBooks", books);
        mv.addObject("categoriesBook", categories);
        mv.addObject("cPublishers", publishers);
        mv.setViewName("frontpage/bookdisplay/Detail");
        return mv;
    }

    @RequestMapping(value="List", method = RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request) {
        String url = "";
        Books books = new Books();
        ModelAndView mv = new ModelAndView();
        String order;
        if(StringUtils.isNotBlank(request.getParameter("order"))) {
            order = request.getParameter("order");
        }
        else {
            order = "PublishDate";
        }
        mv.addObject("order", order);
        if(StringUtils.isNotBlank(request.getParameter("keyword"))) {
            String keyword = request.getParameter("keyword");
            url = url + "keyword=" + keyword;
            books.setTitle(keyword);
            books.setAuthor(keyword);
        }
        if(StringUtils.isNotBlank(request.getParameter("categoryId"))) {
            String categoryId = request.getParameter("categoryId");
            if(url.equals(""))
            url = url + "categoryId=" + categoryId;
            else {
                url = url + "&categoryId=" + categoryId;
            }
            books.setCategoryid(Integer.valueOf(categoryId));
        }
        List<Books> booksList1 =  booksService.selectByMult(books);
        Integer allpage = booksList1.size();
        Integer pageIndex;
        if(StringUtils.isNotBlank(request.getParameter("pageIndex"))) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
            if(pageIndex * 5 > allpage) {
                pageIndex = (allpage % 5 == 0) ? (allpage / 5) : (allpage / 5) + 1;
            }
        }
        else {
            pageIndex = 1;
        }
        List<Books> booksList = booksService.selectByCountry(books, pageIndex, 5, order);
        PageBean<Books> booksPageBean = new PageBean<Books>(booksList);
        mv.addObject("booksList", booksPageBean);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("url", url);
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.setViewName("frontpage/bookdisplay/List");
        return mv;
    }


}
