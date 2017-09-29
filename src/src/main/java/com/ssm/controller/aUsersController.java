package com.ssm.controller;

import com.ssm.controller.common.BaseController;
import com.ssm.model.Users;
import com.ssm.service.impl.UsersServiceImpl;
import com.ssm.shiro.token.manager.TokenManager;
import com.ssm.utils.LoggerUtils;
import com.ssm.utils.PageBean;
import com.ssm.utils.VerifyCodeUtils;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping(value="aUsers")
public class aUsersController extends BaseController{

    @Resource
    UsersServiceImpl usersService;

    @RequestMapping(value="List", method = RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request) {
        String previousLink = "/aUsers/List?";
        String nextLink = "/aUsers/List?";
        Users users = new Users();
        ModelAndView mv = new ModelAndView();

        Integer pageIndex;
        if(request.getParameter("pageIndex") != null) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        else {
            pageIndex = 1;
        }
        List<Users> usersList = usersService.selectAll();
        int sum = usersList.size();
        if(sum < pageIndex * 10) {
            pageIndex = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;
        }
        List<Users> usersList1 = usersService.selectFPage(pageIndex, 10);
        PageBean<Users> usersPageBean = new PageBean<>(usersList1);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("usersList", usersPageBean);
        mv.setViewName("frontpage/admin/UserList");
        return mv;
    }


    @RequestMapping(value="Delete", method = RequestMethod.GET)
    public ModelAndView deleteone(HttpServletRequest request) {
        String previousLink = "/aUsers/List?";
        String nextLink = "/aUsers/List?";
        ModelAndView mv = new ModelAndView();
        Integer iid = Integer.valueOf(request.getParameter("id"));
        usersService.delete(iid);
        Users users = new Users();
        Integer pageIndex = 1;
        List<Users> usersList1 = usersService.selectFPage(1, 10);
        PageBean<Users> usersPageBean = new PageBean<>(usersList1);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("usersList", usersPageBean);
        mv.setViewName("frontpage/admin/UserList");
        return mv;
    }


    @RequestMapping(value="Detail", method = RequestMethod.GET)
    public ModelAndView xiugaione(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer a = Integer.valueOf(request.getParameter("id"));
        Users users = usersService.selectByKey(a);
        Date date = users.getBirthday();
        SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate=sdf.format(date);
        mv.addObject("birthday" ,sDate);
        mv.addObject("user", users);
        mv.setViewName("frontpage/admin/UserDetail");
        return mv;
    }

    @RequestMapping(value="List2", method = RequestMethod.GET)
    public ModelAndView getSteteList(HttpServletRequest request) {
        String previousLink = "/aUsers/List2?";
        String nextLink = "/aUsers/List2?";
        Users users = new Users();
        ModelAndView mv = new ModelAndView();

        Integer pageIndex;
        if(request.getParameter("pageIndex") != null) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        else {
            pageIndex = 1;
        }
        List<Users> usersList = usersService.selectAll();
        int sum = usersList.size();
        if(sum < pageIndex * 10) {
            pageIndex = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;
        }
        List<Users> usersList1 = usersService.selectFPage(pageIndex, 10);
        PageBean<Users> usersPageBean = new PageBean<>(usersList1);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("usersList", usersPageBean);
        mv.setViewName("frontpage/admin/StateList");
        return mv;
    }

    @RequestMapping(value="change", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeUsers(Users entity, HttpServletRequest request, HttpSession session) {
        resultMap.put("status", 400);
        int flag = 0;
        try {
            flag = usersService.updateNotNull(entity);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
        LoggerUtils.fmtDebug(getClass(), "修改完毕！", JSONObject.fromObject(1).toString());
        resultMap.put("message", "修改成功！");
        resultMap.put("status", 200);
        return resultMap;
    }

    private boolean changeUserState(Integer id, String flag) {
        Users user = new Users();
        user.setId(id);
        if(flag.equals("true")) {
            user.setUserstateid(1);
        }
        else {
            user.setUserstateid(2);
        }
        usersService.updateNotNull(user);
        return true;
    };
    @RequestMapping(value="Enable", method=RequestMethod.POST)
    public ModelAndView EnableUser(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String[] check= request.getParameterValues("checkstate");
        for (int i = 0; i < check.length; i++) {
            changeUserState(Integer.valueOf(check[i]), "true");
        }

        Integer pageIndex;
        if(request.getParameter("pageIndex") != null) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        else {
            pageIndex = 1;
        }
        List<Users> usersList = usersService.selectAll();
        int sum = usersList.size();
        if(sum < pageIndex * 10) {
            pageIndex = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;
        }
        List<Users> usersList1 = usersService.selectFPage(pageIndex, 10);
        PageBean<Users> usersPageBean = new PageBean<>(usersList1);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("usersList", usersPageBean);
        mv.setViewName("frontpage/admin/UserList");
        return mv;
    }

    @RequestMapping(value="Disable", method=RequestMethod.POST)
    public ModelAndView DisableUser(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String[] check= request.getParameterValues("checkstate");
        for (int i = 0; i < check.length; i++) {
            changeUserState(Integer.valueOf(check[i]), "false");
        }
        Integer pageIndex;
        if(request.getParameter("pageIndex") != null) {
            pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        }
        else {
            pageIndex = 1;
        }
        List<Users> usersList = usersService.selectAll();
        int sum = usersList.size();
        if(sum < pageIndex * 10) {
            pageIndex = sum % 10 == 0 ? sum / 10 : sum / 10 + 1;
        }
        List<Users> usersList1 = usersService.selectFPage(pageIndex, 10);
        PageBean<Users> usersPageBean = new PageBean<>(usersList1);
        mv.addObject("pageindex", pageIndex);
        mv.addObject("usersList", usersPageBean);
        mv.setViewName("frontpage/admin/UserList");
        return mv;
    }
}
