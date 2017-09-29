package com.ssm.controller;

import com.ssm.model.Categories;
import com.ssm.model.Temporarycart;
import com.ssm.model.Users;
import com.ssm.service.CategoriesService;
import com.ssm.service.TemporaryCartService;
import com.ssm.service.UsersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShowDateController {
    @Resource
    TemporaryCartService temporaryCartService;

    @Resource
    CategoriesServiceImpl categoriesService;

    @Resource
    UsersService usersService;
}
