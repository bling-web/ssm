package com.cc.work3.web_servlet;

import com.cc.work3.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodsDel")
public class GoodsDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id.
        String id = request.getParameter("id");
        //调用逻辑层完成删除操作.
        GoodsService.delete(id);
        //删除之后跳转主页面
        response.sendRedirect(request.getContextPath()+"/Goods_servlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
