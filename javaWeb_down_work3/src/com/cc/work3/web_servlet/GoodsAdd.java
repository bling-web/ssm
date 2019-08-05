package com.cc.work3.web_servlet;

import com.alibaba.druid.util.StringUtils;
import com.cc.work3.DoMain.Goods;
import com.cc.work3.Utils.DateUtil;
import com.cc.work3.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoodsAdd")
public class GoodsAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        //接收参数
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String weight = request.getParameter("weight");
        String size = request.getParameter("size");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String addDate = request.getParameter("addDate");
        //校验参数
        if(StringUtils.isEmpty(number)||StringUtils.isEmpty(name)||StringUtils.isEmpty(weight)||StringUtils.isEmpty(size)||
                StringUtils.isEmpty(quantity)||StringUtils.isEmpty(price)||StringUtils.isEmpty(addDate)){
            request.setAttribute("error_mes","任一输入框内容均不能为空,请检查后提交");
            request.getRequestDispatcher("/add.jsp").forward(request,response);
        }
        //封装参数
        Goods goods=new Goods();
        goods.setNumber(number);
        goods.setName(name);
        goods.setWeight(weight);
        goods.setSize(size);
        goods.setQuantity(quantity);
        goods.setPrice(price);
        goods.setAddDate(DateUtil.parseDate(addDate));
        //调用逻辑层查询是否有该商品?
        boolean flag = GoodsService.queryGood(goods);
        if(flag==true){
            //已经有该商品,增加数量
            GoodsService.addQuantity(goods);
        }else{
            //没有该商品,增加商品
            GoodsService.add(goods);
        }
        //跳转主页面
        response.sendRedirect(request.getContextPath()+"/Goods_servlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
