package com.cc.work3.DAO;

import com.cc.work3.DoMain.Goods;
import com.cc.work3.Utils.JdbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GoodsDAO {
    static JdbcTemplate jdbcTemplate=new JdbcTemplate(JdbUtil.getDataSource());
    public static List<Goods> queryAll() {
        String str="select id,number,name,weight,size,quantity,price,addDate from goods";
        return jdbcTemplate.query(str,new BeanPropertyRowMapper<>(Goods.class));
    }

    public static List<Goods> queryGood(Goods goods) {
        String str="select number,weight,size from goods where name=?";
        Object[] objects = new Object[]{goods.getName()};
        return jdbcTemplate.query(str,objects,new BeanPropertyRowMapper<>(Goods.class));
    }

    public static void addQuantity(Goods goods) {
        //获取原有商品数量
        String str1="select quantity from goods where name=?";
        Object[] objects = new Object[]{goods.getName()};
        List<Goods> good_que = jdbcTemplate.query(str1, objects, new BeanPropertyRowMapper<>(Goods.class));
        int db_que = Integer.parseInt(good_que.get(0).getQuantity());
        int pre_que = Integer.parseInt(goods.getQuantity());
        String now_que=(db_que+pre_que)+"";
        //对商品进行更新
        String str="UPDATE goods SET quantity=? WHERE NAME=?";
        Object[] obj = new Object[]{now_que,goods.getName()};
        jdbcTemplate.update(str,obj);


    }

    public static void addGood(Goods goods) {
        String str="INSERT INTO goods (number,name,weight,size,quantity,price,addDate) VALUES (?,?,?,?,?,?,?)";
        Object[] obj = new Object[]{goods.getNumber(),goods.getName(),goods.getWeight(),goods.getSize(),goods.getQuantity(),
                                        goods.getPrice(),goods.getAddDate()};
        jdbcTemplate.update(str,obj);
    }

    public static void deleteGood(String id) {
        String str="delete from goods where id=?";
        jdbcTemplate.update(str,id);
    }

    public static void uptGood(Goods goods,String id) {
        String str="update goods set number=?,name=?,weight=?,size=?,quantity=?,price=?,addDate=? where id=?";
        jdbcTemplate.update(str,goods.getNumber(),goods.getName(),goods.getWeight(),goods.getSize(),goods.getQuantity(),
                              goods.getPrice(),goods.getAddDate(),id);
    }

    public static Goods queryGoodById(String id) {
        String str="select number,name,weight,size,quantity,price,addDate from goods where id=?";
        Object[] objects = new Object[]{id};
        return jdbcTemplate.queryForObject(str,objects,new BeanPropertyRowMapper<>(Goods.class));
    }
}
