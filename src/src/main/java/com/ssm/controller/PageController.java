package com.ssm.controller;

import com.ssm.model.Categories;
import com.ssm.model.Temporarycart;
import com.ssm.model.Users;
import com.ssm.service.TemporaryCartService;
import com.ssm.service.UsersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.utils.StringUtils;
import io.swagger.models.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
@ApiIgnore
public class PageController {

    @Resource(name="categoriesService")
    CategoriesServiceImpl categoriesService;

    @Resource
    TemporaryCartService temporaryCartService;

    @Resource
    UsersService usersService;
    //首页跳转到api页面
    @RequestMapping("index")
    public ModelAndView home(HttpSession session){
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.setViewName("frontpage/homepage");
        if(StringUtils.isBlank(session.getAttribute("kyUserName"))) {
            return mv;
        }
        String userName = session.getAttribute("kyUserName").toString();
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginid", userName);
        List<Users> usersList = usersService.selectByExample(example);
        Users user = usersList.get(0);
        Example example2 = new Example(Temporarycart.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("userid", user.getId());
        List<Temporarycart> temporarycartList = temporaryCartService.selectByExample(example2);
        Integer num = temporarycartList.size();
        if(num.equals(0)) {
            session.removeAttribute("kyCart");
        }
        else {
            session.setAttribute("kyCart", num);
        }
        return mv;
    }

    @RequestMapping("ShoppingCart")
    public ModelAndView Cart() {
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        return new ModelAndView("frontpage/cart/ShoppingCart");
    }

    @RequestMapping("adminSignin")
    public ModelAndView adSignin() {return new ModelAndView("frontpage/admin/signin");}

    @RequestMapping("adminindex")
    public ModelAndView adminhome() {return new ModelAndView("frontpage/admin/homepage");}

    @RequestMapping("adminLogout")
    public ModelAndView adminout(HttpSession session) {
        session.removeAttribute("kyAdminName");
        return new ModelAndView("frontpage/admin/signin");
    }
}
