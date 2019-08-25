package com.cc.springMVC.Controller;

import com.cc.springMVC.Domain.User;
import com.cc.springMVC.Service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("login")
public class LoginController {
    private int login_help=0;  //当此变量大于等于3时,需要验证码登录.
    static private HashMap<String,Integer> map;
    static{
        //在程序加载的时候初始化一个容器,来绑定每一个ip的login_help.
         map = new HashMap<>();
    }

    @Resource
    private UserService userService;
    @GetMapping("to")
    public String toLogin(HttpServletRequest request){
        //验证是否登录成功
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if(cookie!=null){
//                if(cookie.getName().equals("have_login_success")){
//                    if(cookie.getValue().equals("true"))
//                        return "redirect:/User/findAll";
//                }
//            }
//        }
        //没有成功回到正常登录页面
        return "login/login";
    }

    /**
     * 验证没有验证码时的登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("verify")
    public String login(User user,HttpServletRequest request,HttpServletResponse response){
        boolean flag = userService.verify(user);
        return login_help(flag,request,response);
    }

    /**
     * 验证有验证码时的登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("verify_code")
    public String login_code(User user,HttpServletRequest request,HttpServletResponse response){
        String verify_code = request.getParameter("verify_code");
        HttpSession session = request.getSession();
        String real_code = (String)session.getAttribute("code");
        if(verify_code.isEmpty()){
            session.setAttribute("error_mes","验证码错误");
            return "login/login_verify";
        }else{
            if(verify_code.equals(real_code)){
                //验证码校验成功,验证用户
                boolean flag = userService.verify(user);
                return login_help(flag,request,response);
            }else{
                session.setAttribute("error_mes","验证码错误");
                return "login/login_verify";
            }
        }

    }


    public String login_help(boolean flag, HttpServletRequest request, HttpServletResponse response){
        //如果没有绑定,进行绑定
        if(!map.containsKey(request.getRemoteAddr())){
            map.put(request.getRemoteAddr(),login_help);
        }
        if(flag==true){
            //登录成功,变量清0
            map.put(request.getRemoteAddr(),0);
            //设置登录成功标志,一遍springMVC拦截器拦截没有登录的请求
            HttpSession session = request.getSession();
            session.setAttribute("login_success",true);
            //使用cookie实现三天免登录功能
            Cookie cookie = new Cookie("have_login_success","true");
            cookie.setMaxAge(3600*24*3);
            response.addCookie(cookie);
            return "redirect:/User/findAll";
        }
        else{
            //登录不成功,每次加1
            map.put(request.getRemoteAddr(),++login_help);
            HttpSession session = request.getSession();
            session.setAttribute("error_mes","用户名或密码错误");
            if(map.get(request.getRemoteAddr())>=3){
                //大于等于3,调到有验证码的登录页面
                return "login/login_verify";
            }else{
                return "login/login";
            }
        }
    }
}
