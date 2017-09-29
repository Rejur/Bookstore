package com.ssm.controller;

import com.ssm.controller.common.BaseController;
import com.ssm.model.*;
import com.ssm.service.*;
import com.ssm.service.impl.OrdersServiceImpl;
import com.ssm.service.impl.TemporaryCartServiceImpl;
import com.ssm.shiro.token.manager.TokenManager;
import com.ssm.utils.CartList;
import com.ssm.utils.PageBean;
import com.ssm.utils.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HTTP;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping("Order")
public class OrderController extends BaseController{

    @Resource
    UsersService usersService;

    @Resource
    TemporaryCartServiceImpl temporaryCartService;

    @Resource
    BooksService booksService;

    @Resource
    OrderBookService orderBookService;

    @Resource
    OrdersService ordersService;

    @RequestMapping(value="addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addOrder(HttpSession session) {
        try {
            String userName = session.getAttribute("kyUserName").toString();
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
                for (CartList cartList : cartListList) {
                    if (cartList.getTitle().equals(books.getTitle())) {
                        cartList.setQuantity(cartList.getQuantity() + 1);
                        sum = sum.add(books.getUnitprice());
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    cartListList.add(new CartList(books.getTitle(), books.getIsbn(), books.getUnitprice(), 1, books.getId()));
                    sum = sum.add(books.getUnitprice());
                }
            }
            if (cartListList.isEmpty()) {
                resultMap.put("status", 500);
                resultMap.put("message", "您的购物车为空");
                return resultMap;
            }
            Orders orders = new Orders();
            orders.setTotalprice(sum);
            orders.setUserid(user.getId());
            Date date = new Date();
            orders.setOrderdate(date);
            ordersService.insertnoid(orders);
            Example example1 = new Example(Orders.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("userid", user.getId());
            example1.setOrderByClause("OrderDate" + " desc");
            List<Orders> ordersList = ordersService.selectByExample(example1);
            Orders orders1 = ordersList.get(0);
            Integer id = orders1.getUserid();
            for (CartList cart: cartListList
                 ) {
                Orderbook orderbook = new Orderbook();
                orderbook.setOrderid(id);
                orderbook.setQuantity(cart.getQuantity());
                orderbook.setUnitprice(cart.getUnitprice());
                orderbook.setBookid(cart.getBookid());
                orderBookService.insertnoid(orderbook);
            }
            Temporarycart temporarycart = new Temporarycart();
            temporarycart.setUserid(user.getId());
            temporaryCartService.del(temporarycart);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resultMap.put("status", 500);
            resultMap.put("message", "下单失败");
            return resultMap;
        }
        resultMap.put("status", 200);
        resultMap.put("message", "下单成功");
        return resultMap;
    }

    @RequestMapping(value="showOrder", method = RequestMethod.GET)
    public ModelAndView showOrder(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer pageIndex = 1;
        if (StringUtils.isNotBlank(request.getParameter("pageIndex"))) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        List<Orders> ordersList = ordersService.selectFPage(pageIndex,10);
        PageBean<Orders> categoriesPageBean = new PageBean<>(ordersList);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("ordersList", categoriesPageBean);
        mv.setViewName("/frontpage/admin/OrderList");
        return mv;
    }

    @RequestMapping(value="Delete", method = RequestMethod.GET)
    public ModelAndView  deleteOrder(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String str = request.getParameter("id");
        Integer id = Integer.valueOf(str);
        Orders orders = new Orders();
        orders.setId(id);
        Example example1 = new Example(Orderbook.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("orderid", orders.getId());
        List<Orderbook> orderbookList = orderBookService.selectByExample(example1);
        for (Orderbook var: orderbookList
             ) {
            Integer id1 = var.getId();
            orderBookService.delete(id1);
        }
        ordersService.delete(id);

        List<Orders> ordersList = ordersService.selectFPage(1,10);
        PageBean<Orders> categoriesPageBean = new PageBean<>(ordersList);
        mv.addObject("pageindex", 1);
        mv.addObject("ordersList", categoriesPageBean);
        mv.setViewName("/frontpage/admin/OrderList");
        return mv;
    }
}
