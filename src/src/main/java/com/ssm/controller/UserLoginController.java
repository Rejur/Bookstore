package com.ssm.controller;

import com.ssm.controller.common.BaseController;
import com.ssm.model.Categories;
import com.ssm.model.Users;
import com.ssm.service.CategoriesService;
import com.ssm.service.UsersService;
import com.ssm.service.impl.CategoriesServiceImpl;
import com.ssm.shiro.token.manager.TokenManager;
import com.ssm.utils.LoggerUtils;
import com.ssm.utils.StringUtils;
import com.ssm.utils.VerifyCodeUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Scope(value="prototype")
@RequestMapping("u")
public class UserLoginController extends BaseController{
    @Resource
    UsersService usersService;

    @Resource
    CategoriesServiceImpl categoriesService;

    @RequestMapping(value="gologin", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.setViewName("frontpage/signin");
        return mv;
    }

    @RequestMapping(value="goregister", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        List<Categories> categoriesList = categoriesService.selectAll();
        mv.addObject("categoriesList", categoriesList);
        mv.setViewName("frontpage/register");
        return mv;
    }

    @RequestMapping(value="submitLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(Users entity, Boolean rememberMe, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            entity = TokenManager.login(entity, rememberMe);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            if(session.getAttribute("kyUserName") == null)
                session.setAttribute("kyUserName", entity.getLoginid());
            if(rememberMe == true) {
                Cookie cookie = new Cookie("user", entity.getLoginid());
                Cookie c = new Cookie("users", entity.getLoginid() + "-" + entity.getLoginpwd());
                //设置过期时间
                c.setMaxAge(600);
                //存储
                response.addCookie(c);
            }
            SavedRequest  savedRequest = WebUtils.getSavedRequest(request);
            String url = null;
            if(savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }

            LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
            if(StringUtils.isBlank(url)) {
                url = request.getContextPath() + "index.do";
            }
            resultMap.put("back_url", url);
        }
        catch (DisabledAccountException e) {
            resultMap.put("status", 500);
            resultMap.put("message", "账号已经禁用.");
        }
        catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "账号或密码错误.");
        }
        return resultMap;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request, HttpSession session) {
        try {
            if(session.getAttribute("kyUserName").toString() != null)
                session.removeAttribute("kyUserName");//清空登录信息
            TokenManager.logout();
            resultMap.put("status", 200);
            SavedRequest  savedRequest = WebUtils.getSavedRequest(request);
            String url = null;
            if(savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }

            LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]",url);
            if(StringUtils.isBlank(url)) {
                url = request.getContextPath() + "index.do";
            }
            resultMap.put("back_url", url);
        } catch (Exception e) {
            resultMap.put("status", 500);
            logger.error("errorMessage:" + e.getMessage());
            LoggerUtils.fmtError(getClass(), e, "退出出现错误，%s。", e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value="subRegister", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> subRegister(Users entity, String vcode, HttpServletRequest request, HttpSession session) {
        resultMap.put("status", 400);
        if(!VerifyCodeUtils.verifyCode(vcode)) {
            resultMap.put("message", "验证码不正确！");
            return resultMap;
        }
        String username = entity.getLoginid(), email = entity.getMail();
        Example example = new Example(Users.class);
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        criteria1.andEqualTo("loginid", username);
        criteria2.andEqualTo("mail", email);
        example.or(criteria2);
        List<Users> usersList = usersService.selectByExample(example);
        if(usersList.size() != 0) {
            resultMap.put("message", "账号|Email已经存在!");
            return resultMap;
        }
        Date date = new Date();
        String requestUrl = request.getRequestURL().toString();
        entity.setRegisterip(requestUrl.substring(7, requestUrl.length()-1));
        entity.setRegistertime(date);
        entity.setUserstateid(1);
        entity.setUserroleid(1);

        int flag = usersService.insertnoid(entity);
        LoggerUtils.fmtDebug(getClass(), "注册插入完毕！", JSONObject.fromObject(flag).toString());
        if(session.getAttribute("kyUserName") == null)
            session.setAttribute("kyUserName", entity.getLoginid());
        entity = TokenManager.login(entity, Boolean.TRUE);
        LoggerUtils.fmtDebug(getClass(), "注册后，登录完毕！", JSONObject.fromObject(entity).toString());
        resultMap.put("message", "注册成功！");
        resultMap.put("status", 200);
        return resultMap;
    }

    @RequestMapping(value="adminLogin", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> adminLogin(Users entity, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            boolean rememberMe = false;
            entity = TokenManager.login(entity, rememberMe);
            if(entity.getUserroleid() != 3) {
                resultMap.put("status", 500);
                resultMap.put("message", "账号非管理员账号");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            if(session.getAttribute("kyAdminName") == null)
                session.setAttribute("kyAdminName", entity.getLoginid());
            SavedRequest  savedRequest = WebUtils.getSavedRequest(request);

            String url = "adminindex";

            resultMap.put("back_url", url);
        }
        catch (DisabledAccountException e) {
            resultMap.put("status", 500);
            resultMap.put("message", "账号已经禁用.");
        }
        catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "账号或密码错误.");
        }
        return resultMap;
    }
}
