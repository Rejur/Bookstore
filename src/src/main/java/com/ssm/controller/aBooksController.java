package com.ssm.controller;

import com.github.pagehelper.Page;
import com.ssm.controller.common.BaseController;
import com.ssm.model.Books;
import com.ssm.model.Categories;
import com.ssm.model.Publishers;
import com.ssm.service.BooksService;
import com.ssm.service.PublishersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.service.impl.PublisherServiceImpl;
import com.ssm.utils.PageBean;
import com.ssm.utils.StringUtils;
import io.swagger.models.auth.In;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@Scope(value="prototype")
@RequestMapping("aBooks")
public class aBooksController extends BaseController {
    @Resource
    BooksService booksService;

    @Resource
    CategoriesServiceImpl categoriesService;

    @Resource
    PublisherServiceImpl publishersService;

    @RequestMapping(value="List", method=RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String url = "";
        Books books = new Books();
        List<Books> booksList = new ArrayList<Books>();
        Integer pageIndex;
        if(StringUtils.isNotBlank(request.getParameter("pageIndex"))) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        else {
            pageIndex = 1;
        }
        if(StringUtils.isNotBlank(request.getParameter("keyword"))) {
            String keyword = request.getParameter("keyword");
            url = "keyword=" + request.getParameter("keyword");
            books.setTitle(keyword);
            books.setAuthor(keyword);
        }
        if(StringUtils.isNotBlank(request.getParameter("categoryId"))) {
            String categoryId = request.getParameter("categoryId");
            if(url.equals("")) {
                url = "categoryId=" + categoryId;
            }
            else {
                url = "&categoryId=" + categoryId;
            }
            books.setCategoryid(Integer.valueOf(categoryId));
        }

        List<Books> booksList1 = booksService.selectByMult(books);
        Integer pagesum = booksList1.size();
        if(pageIndex * 10 > pagesum) {
            pageIndex = (pagesum % 10) == 0 ? pagesum / 10 : (pagesum / 10) + 1;
        }
        List<Books> booksList2 = booksService.selectByCountry(books, pageIndex, 10, "id");
        List<Categories> categoriesList = categoriesService.selectAll();
        PageBean<Books> booksPageBean = new PageBean<>(booksList2);
        for (Categories categories: categoriesList
             ) {
            mv.addObject("categories" + categories.getId().toString(), categories.getName());
        }
        mv.addObject("pageindex",  pageIndex);
        mv.addObject("url", url);
        mv.addObject("categories", categoriesList);
        mv.addObject("booksPageBean", booksPageBean);
        mv.setViewName("/frontpage/admin/BooksList");
        return mv;
    }

    @RequestMapping(value="List2", method = RequestMethod.GET)
    public ModelAndView getList2(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer pageIndex = 1;
        if (StringUtils.isNotBlank(request.getParameter("pageIndex"))) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        List<Categories> categoriesList = categoriesService.selectFPage(pageIndex,10);
        PageBean<Categories> categoriesPageBean = new PageBean<Categories>(categoriesList);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("categoriesList", categoriesPageBean);
        mv.setViewName("/frontpage/admin/CategoriesList");
        return mv;
    }

    @RequestMapping(value="addBook", method = RequestMethod.GET)
    public ModelAndView addBook(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        List<Publishers> publishersList = publishersService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.addObject("publishersList", publishersList);
        mv.setViewName("/frontpage/admin/AddBook");
        return mv;
    }

    @RequestMapping(value="insertBook", method = RequestMethod.POST)
    public ModelAndView inBook(HttpServletRequest request, @RequestParam(value = "file", required = false)MultipartFile image) throws ParseException {
        ModelAndView mv = new ModelAndView();
        try {
            String title = request.getParameter("Title");
            String unitPrice = request.getParameter("UnitPrice");
            String publisherId = request.getParameter("PublisherId");
            String categoryId = request.getParameter("CategoryId");
            String author = request.getParameter("Author");
            String isbn = request.getParameter("ISBN");
            String publishDate = request.getParameter("PublishDate");
            String contentDescription = request.getParameter("ContentDescription");
            String toc = request.getParameter("TOC");
            try {
//        String fileName = new Date().getTime()+".jpg";
                //String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/bookstore/Images/BookCovers");
                //File targetFile = new File(savePath, fileName);
                //if(!targetFile.exists()){
                    //targetFile.mkdirs();
                //}

                //保存
                //try {
                    //image.transferTo(targetFile);
                //} catch (Exception e) {
                    //e.printStackTrace();
                //}
                InputStream in = request.getInputStream();


                String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/bookstore/Images/BookCovers");
                File f = new File(savePath, isbn + "2.jpg");
                FileOutputStream o = new FileOutputStream(f);
                byte b[] = new byte[1024];
                int n;
                while ((n = in.read(b)) != -1) {
                    o.write(b, 0, n);
                }
                o.close();
                in.close();
            } catch (IOException ee) {
            }

            Books books = new Books();
            books.setCategoryid(Integer.valueOf(categoryId));
            books.setAuthor(author);
            books.setTitle(title);
            books.setContentdescription(contentDescription);
            books.setToc(toc);
            books.setPublisherid(Integer.valueOf(publisherId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            java.util.Date date = sdf.parse(publishDate);
            books.setPublishdate(date);
            books.setIsbn(isbn);
            books.setUnitprice(new BigDecimal(unitPrice));
            books.setClicks(0);
            booksService.insertnoid(books);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "提交错误");
        }
        List<Categories> categoriesList = categoriesService.selectAll();
        List<Publishers> publishersList = publishersService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.addObject("publishersList", publishersList);
        mv.setViewName("frontpage/admin/AddBook");
        return mv;
    }

    @RequestMapping(value="Delete", method = RequestMethod.GET)
    public ModelAndView dellBook(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer key = Integer.valueOf(request.getParameter("id"));
        booksService.delete(key);
        mv = getList(request);
        return mv;
    }

    @RequestMapping(value="Detail", method = RequestMethod.GET)
    public ModelAndView bookDetail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        List<Publishers> publishersList = publishersService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.addObject("publishersList", publishersList);
        Integer key =  Integer.valueOf(request.getParameter("id"));
        Books books = booksService.selectByKey(key);
        mv.addObject("cb", books);
        mv.setViewName("/frontpage/admin/BookDetail");
        return mv;
    }

    @RequestMapping(value="modifybook", method = RequestMethod.POST)
    public ModelAndView changebook(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            Integer id = Integer.valueOf(request.getParameter("Id"));
            String title = request.getParameter("Title");
            String unitPrice = request.getParameter("UnitPrice");
            String publisherId = request.getParameter("PublisherId");
            String categoryId = request.getParameter("CategoryId");
            String author = request.getParameter("Author");
            String isbn = request.getParameter("ISBN");
            String publishDate = request.getParameter("PublishDate");
            String contentDescription = request.getParameter("ContentDescription");
            String toc = request.getParameter("TOC");
            try {
                InputStream in = request.getInputStream();


                String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/bookstore/Images/BookCovers");
                File f = new File(savePath, isbn + ".jpg");
                FileOutputStream o = new FileOutputStream(f);
                byte b[] = new byte[1024];
                int n;
                while ((n = in.read(b)) != -1) {
                    o.write(b, 0, n);
                }
                o.close();
                in.close();
            } catch (IOException ee) {
            }
            Books books = new Books();
            books.setId(id);
            books.setCategoryid(Integer.valueOf(categoryId));
            books.setAuthor(author);
            books.setTitle(title);
            books.setContentdescription(contentDescription);
            books.setToc(toc);
            books.setPublisherid(Integer.valueOf(publisherId));
            SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf2.parse(sdf2.format(sdf1.parse(publishDate)));
            books.setPublishdate(date);
            books.setIsbn(isbn);
            books.setUnitprice(new BigDecimal(unitPrice));
            books.setClicks(0);
            booksService.updateNotNull(books);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改错误");
        }
        finally {
            List<Categories> categoriesList = categoriesService.selectAll();
            List<Publishers> publishersList = publishersService.selectAll();
            mv.addObject("categoriesList", categoriesList);
            mv.addObject("publishersList", publishersList);
            Integer key =  Integer.valueOf(request.getParameter("Id"));
            Books books = booksService.selectByKey(key);
            mv.addObject("cb", books);
            mv.setViewName("frontpage/admin/BookDetail");
            return mv;
        }
    }

    @RequestMapping(value="addCategories", method = RequestMethod.GET)
    public ModelAndView addCategories(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String name = request.getParameter("categoryName");
        Example example = new Example(Categories.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        List<Categories> categoriesList1 = categoriesService.selectByExample(example);
        if(categoriesList1.size() != 0) {
            JOptionPane.showMessageDialog(null, "该分类已存在");
        }
        else {
            Categories categories = new Categories();
            categories.setName(name);
            categoriesService.insertnoid(categories);
        }
        Integer pageIndex = 1;
        if (StringUtils.isNotBlank(request.getParameter("pageIndex"))) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        List<Categories> categoriesList = categoriesService.selectFPage(pageIndex,10);
        PageBean<Categories> categoriesPageBean = new PageBean<Categories>(categoriesList);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("categoriesList", categoriesPageBean);
        mv.setViewName("/frontpage/admin/CategoriesList");
        return mv;
    }
}
