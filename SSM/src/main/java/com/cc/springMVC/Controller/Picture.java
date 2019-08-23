package com.cc.springMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

@Controller
@RequestMapping("picture")
public class Picture  {
    @GetMapping("get")
    public void get(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取图片对象
        int width=100;
        int height=50;
        BufferedImage bufferedImage = new BufferedImage(width,height,TYPE_INT_RGB);
        //获取画笔对象
        Graphics graphics = bufferedImage.getGraphics();
        //填充颜色
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,width,height);
        //画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,height-1);
        //写文字
        graphics.setColor(Color.black);
        String flag="QWERTYIOPASDFGHJKLZXCVBNMzxcvbnmasdfghjklqwertyuiop0123456789";
        String verify_code="";
        Random random = new Random();
        for (int i = 1; i < 5; i++) {
            int chatat = random.nextInt(flag.length());
            char c=flag.charAt(chatat);
            verify_code += c;
            graphics.drawString(c+"",width/5*i,height/2);
        }
        HttpSession session = request.getSession();                                  //传输数据
        session.setAttribute("code",verify_code);
        //画干扰线
        graphics.setColor(Color.red);
        int x1=random.nextInt(width);
        int x2=random.nextInt(width);
        int y1=random.nextInt(height);
        int y2=random.nextInt(height);
        graphics.drawLine(x1,y1,x2,y2);
        //输出对象
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());

    }
}

