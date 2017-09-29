package com.ssm.controller;

import com.ssm.controller.common.BaseController;
import com.ssm.model.Books;
import com.ssm.model.Categories;
import com.ssm.model.Temporarycart;
import com.ssm.model.Users;
import com.ssm.service.BooksService;
import com.ssm.service.TemporaryCartService;
import com.ssm.service.UsersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.utils.CartList;
import com.ssm.utils.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@Scope(value="prototype")
@RequestMapping("Cart")
public class CartController extends BaseController{

    @Resource
    CategoriesServiceImpl categoriesService;

    @Resource
    TemporaryCartService temporaryCartService;

    @Resource
    UsersService usersService;

    @Resource
    BooksService booksService;

    @RequestMapping(value="scan", method=RequestMethod.GET)
    public ModelAndView goCart(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String userName;
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        if(StringUtils.isBlank(session.getAttribute("kyUserName"))) {
            categoriesList = categoriesService.selectAll();
            mv.addObject("categoriesList", categoriesList);
            mv.setViewName("frontpage/signin");
            return mv;
        }
        else {
            userName = session.getAttribute("kyUserName").toString();
        }
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginid", userName);
        List<Users> usersList = usersService.selectByExample(example);
        Users user = usersList.get(0);
        Example example2 = new Example(Temporarycart.class);
        Example.Criteria criteria2 = example2.createCriteria();
        Integer a = user.getId();
        criteria2.andEqualTo("userid", a);
        List<Temporarycart> temporarycartList = temporaryCartService.selectByExample(example2);
        List<CartList> cartListList = new ArrayList<CartList>();
        int flag = 0;
        BigDecimal sum = new BigDecimal(0);
        for (Temporarycart var : temporarycartList) {
            flag = 0;
            Books books = booksService.selectByKey(var.getBookid());
            for (CartList cartList: cartListList) {
                if(cartList.getTitle().equals(books.getTitle())) {
                    cartList.setQuantity(cartList.getQuantity() + 1);
                    sum = sum.add(books.getUnitprice());
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                cartListList.add(new CartList(books.getTitle(), books.getIsbn(), books.getUnitprice(), 1, books.getId()));
                sum = sum.add(books.getUnitprice());
            }
        }
        if(!cartListList.isEmpty())
            mv.addObject("cartList", cartListList);
        mv.addObject("sumValue", sum.toString());
        mv.setViewName("frontpage/cart/shoppingcart");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView addBookCart(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String userName;
        if(StringUtils.isBlank(session.getAttribute("kyUserName"))) {
            List<Categories> categoriesList = categoriesService.selectAll();
            mv.addObject("categoriesList", categoriesList);
            mv.setViewName("frontpage/signin");
            return mv;
        }
        else {
            userName = session.getAttribute("kyUserName").toString();
        }
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginid", userName);
        List<Users> usersList = usersService.selectByExample(example);
        Users user = usersList.get(0);
        Temporarycart temporarycart = new Temporarycart();
        temporarycart.setUserid(user.getId());
        Integer bid = Integer.valueOf(request.getParameter("bid"));
        temporarycart.setBookid(bid);
        Date date = new Date();
        temporarycart.setCreatetime(date);
        temporaryCartService.insertnoid(temporarycart);
        Example example2 = new Example(Temporarycart.class);
        Example.Criteria criteria2 = example2.createCriteria();
        Integer a = user.getId();
        criteria2.andEqualTo("userid", a);
        List<Temporarycart> temporarycartList = temporaryCartService.selectByExample(example2);
        List<CartList> cartListList = new ArrayList<CartList>();
        int flag = 0;
        BigDecimal sum = new BigDecimal(0);
        for (Temporarycart var : temporarycartList) {
            flag = 0;
            Books books = booksService.selectByKey(var.getBookid());
            for (CartList cartList: cartListList) {
                if(cartList.getTitle().equals(books.getTitle())) {
                    cartList.setQuantity(cartList.getQuantity() + 1);
                    sum = sum.add(books.getUnitprice());
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                cartListList.add(new CartList(books.getTitle(), books.getIsbn(), books.getUnitprice(), 1, books.getId()));
                sum = sum.add(books.getUnitprice());
            }
        }
        mv.addObject("cartList", cartListList);
        mv.addObject("sumValue", sum.toString());
        mv.setViewName("frontpage/cart/shoppingcart");
        return mv;
    }

    @RequestMapping(value="delete", method = RequestMethod.GET)
    public ModelAndView delBook(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String usrname = session.getAttribute("kyUserName").toString();
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginid", usrname);
        List<Users> usersList = usersService.selectByExample(example);
        Users user = usersList.get(0);

        String title = request.getParameter("bookid");
        Example example2 = new Example(Books.class);
        Example.Criteria criteria1 = example2.createCriteria();
        criteria1.andEqualTo("id", title);
        List<Books> booksList = booksService.selectByExample(example2);
        Books books = booksList.get(0);
        Temporarycart temporarycart = new Temporarycart();
        temporarycart.setBookid(books.getId());
        temporarycart.setUserid(user.getId());
        temporaryCartService.delB(temporarycart);
        mv = goCart(session);
        return mv;
    }
}
