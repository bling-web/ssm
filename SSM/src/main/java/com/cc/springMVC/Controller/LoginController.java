package com.cc.springMVC.Controller;

import com.cc.springMVC.Domain.User;
import com.cc.springMVC.Service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public String toLogin(){
        return "login/login";
    }

    /**
     * 验证没有验证码时的登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("verify")
    public String login(User user,HttpServletRequest request){
        boolean flag = userService.verify(user);
        return login_help(flag,request);
    }

    /**
     * 验证有验证码时的登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("verify_code")
    public String login_code(User user,HttpServletRequest request){
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
                return login_help(flag,request);
            }else{
                session.setAttribute("error_mes","验证码错误");
                return "login/login_verify";
            }
        }

    }


    public String login_help(boolean flag,HttpServletRequest request){
        //如果没有绑定,进行绑定
        if(!map.containsKey(request.getRemoteAddr())){
            map.put(request.getRemoteAddr(),login_help);
        }
        if(flag==true){
            //登录成功,变量清0
            map.put(request.getRemoteAddr(),0);
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
