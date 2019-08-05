package com.cc.work3.web_servlet;

import com.alibaba.druid.util.StringUtils;
import com.cc.work3.DoMain.User;
import com.cc.work3.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login_verify")
public class login_verify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        //接收参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verify_code = request.getParameter("verify_code");
        //校验参数,用户名密码,已经用正则表达式效验,肯定不为空
        if(StringUtils.isEmpty(verify_code)){
            forwordPage("验证码不能为空,请重新输入","/login.jsp",request,response);
            return;
        }
        //封装参数
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        //进行验证码校验
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if(code.equalsIgnoreCase(verify_code)){
            //进行账号密码校验
            boolean flag = UserService.verifyUser(user);
            if(flag==true) {
                //登陆成功,重定向
                session.setAttribute("username",username);
                response.sendRedirect(request.getContextPath()+"/Goods_servlet");
            }
            else{
                forwordPage("账号或密码错误,请重新输入","/login.jsp",request,response);
                return;
            }
        }else{
            forwordPage("验证码不正确,请重新输入","/login.jsp",request,response);
        }

    }
    protected  void forwordPage(String message,String uri,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error_mes",message);
        request.getRequestDispatcher(uri).forward(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
