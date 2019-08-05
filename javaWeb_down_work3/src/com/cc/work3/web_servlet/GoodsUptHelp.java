package com.cc.work3.web_servlet;

import com.cc.work3.DoMain.Goods;
import com.cc.work3.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodsUptHelp")
public class GoodsUptHelp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String id = request.getParameter("id");
        //查找该id对应的属性,使其修改时直接显示在输入框内,增加体验
        //调用逻辑层完成查找.
        Goods goods = GoodsService.queryById(id);
        request.setAttribute("id",id);
        request.setAttribute("number",goods.getNumber());
        request.setAttribute("name",goods.getName());
        request.setAttribute("weight",goods.getWeight());
        request.setAttribute("size",goods.getSize());
        request.setAttribute("quantity",goods.getQuantity());
        request.setAttribute("price",goods.getPrice());
        request.setAttribute("addDate",goods.getAddDate());
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
