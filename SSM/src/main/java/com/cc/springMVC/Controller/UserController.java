package com.cc.springMVC.Controller;

import com.cc.springMVC.Domain.User;
import com.cc.springMVC.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("User")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("findAll")
    public String findAll(Model model){        //放在model对象中的会放在request对象中
        List<User> all = userService.findAll();
        for(User user:all){
            if(user.getSex().equals("1"))
                user.setSex("男");
            if(user.getSex().equals("2"))
                user.setSex("女");
        }
        model.addAttribute("user",all);
        return "login/main";
    }

    /**
     * 增加用户的方法
     * @param user
     * @return
     */
    @PostMapping("insert")
    public String insert(User user){
        userService.insert(user);
        return "redirect:/User/findAll";
    }
    //帮助转发到add页面
    @GetMapping("add")
    public String toAddPage(){
        return "user/add";
    }

    //帮助转发到userRegister页面
    @GetMapping("userRegister")
    public String toUserRegister(){
        return "user/userRegister";
    }

    /**
     * 处理用户传回来的注册数据
     * @param user
     * @param request
     * @param Myfile
     * @return
     */
    @PostMapping("addUser")
    public String uplatesPicture(User user,HttpServletRequest request, MultipartFile Myfile){
        //创建这个文件路径对象
        File file=new File("D:\\javaPrograming\\SSM-\\src\\main\\webapp\\static\\uploads");
        if(!file.exists()) {
            file.mkdir();
        }//不存在则创建文件夹,以上几步都是为了创建一个文件夹
        String Filename = Myfile.getOriginalFilename();
        //加一个编号,防止文件名称一样
        int flag=0;flag++;
        String id = flag+"";
        String name=id+Filename;
        request.setAttribute("name",name);
        //把request中文件转移到本地中来
        try {
            Myfile.transferTo((new File(file,name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用增加对象方法
        boolean if_success = addUser(name, user, request);
        if(if_success==false){
            return "user/userRegister";
        }else{
            return "redirect:/User/findAll";
        }
    }

    /**
     * 增加对象的方法
     * @param name 用户头像的名字
     * @param user
     * @param request
     * @return
     */
    private boolean addUser(String name, User user,HttpServletRequest request) {
        //验证字段是否为空
        if(user.getEmail().isEmpty()||user.getPhone().isEmpty()||user.getUsername().isEmpty()
                ||user.getPassword().isEmpty()||user.getName().isEmpty()){
            request.setAttribute("error_user", "任一字段均不能为空,请检查后提交!");
            return false;
        }
        //验证验证码是否正确
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("code");
        String identifying_code = (String)request.getParameter("identifying_code");
        if(code.equalsIgnoreCase(identifying_code)){
            user.setPicture(name);
            //调用查询方法查询名称,邮箱,手机号是否重复
            boolean flag = userService.verifyColumn(user);
            if(flag==false) {
                request.setAttribute("error_user", "用户名/手机号/邮箱 已经被人注册,请更改信息!");
                return false;
            }else{
                //调用service层增加用户
                userService.insert(user);
                return true;
            }
        }else{
            request.setAttribute("error_user","验证码错误,请重新输入!");
            return false;
        }
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
    @GetMapping("userDel")
    public String userDel(String id){
        //调用service.删除该条纪录
        userService.delete(id);
        //返回主页面
        return "redirect:/User/findAll";
    }

    /**
     * 更改资料方法
     * @param id
     * @param request
     * @return
     */
    @GetMapping("userUpdate")
    public String userUpdate(String id,HttpServletRequest request){
        //先根据id查询出内容来,把内容显示到修改页面中
        User user = userService.findById(id);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        //转发到注册页面
        return "user/userRegister";
    }
}
