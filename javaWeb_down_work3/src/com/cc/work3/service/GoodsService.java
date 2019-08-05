package com.cc.work3.service;

import com.cc.work3.DAO.GoodsDAO;
import com.cc.work3.DAO.UserDao;
import com.cc.work3.DoMain.Goods;
import com.cc.work3.DoMain.User;

import java.util.List;

public class GoodsService {


    public static List<Goods> queryAll() {
        //调用数据层查询
        return GoodsDAO.queryAll();

    }

    public static boolean queryGood(Goods goods) {
        List<Goods> que_goods = GoodsDAO.queryGood(goods);
        for (Goods que_good : que_goods) {
            //如果商品名称,编号,重量,大小都相同,则认为是相同的商品
           if(que_good.getWeight().equals(goods.getWeight())&&que_good.getSize().equals(goods.getSize())&&que_good.getNumber().equals(goods.getNumber())){
                return true;
            }
        }
        return false;
    }

    public static void addQuantity(Goods goods) {
        GoodsDAO.addQuantity(goods);
    }

    public static void add(Goods goods) {
        GoodsDAO.addGood(goods);
    }

    public static void delete(String id) {
        GoodsDAO.deleteGood(id);

    }

    public static void upt(Goods goods,String id) {
        GoodsDAO.uptGood(goods,id);
    }

    public static Goods queryById(String id) {
        Goods goods = GoodsDAO.queryGoodById(id);
        return goods;
    }
}
