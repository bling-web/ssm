package com.cc.work3.web_servlet;

import com.cc.work3.DoMain.Goods;
import com.cc.work3.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Goods_servlet")
public class Goods_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        //调用业务逻辑层完成显示
        List<Goods> goods = GoodsService.queryAll();
        HttpSession session = request.getSession();
        session.setAttribute("goods",goods);
        response.sendRedirect(request.getContextPath()+"/main.jsp");
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
