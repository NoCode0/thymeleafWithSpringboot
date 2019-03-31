package com.cheng.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cheng.thymeleaf.domain.User;
import com.cheng.thymeleaf.respository.UserRespository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRespository userRespository;

    /**
     * 查询所有用户
     * @param model
     * @return
     */
    @GetMapping()
    public ModelAndView list(Model model){
        System.out.println("list");
        model.addAttribute("userList",userRespository.listUsers());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list","userModel",model);
    }
    
    /**
     * @Description 根据id查询用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id,Model model){
        model.addAttribute("user",userRespository.getUserById(id));
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view","userModel",model);
    }
    
    /**
     * @Description 获取创建表单用户
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        System.out.println("to Form");
        model.addAttribute("user",new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form","userModel",model);
    }
    
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        System.out.println("saveOrUpdateUser");
        user = userRespository.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/users");
    }
    
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        System.out.println("delete");
        userRespository.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }
    
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id,Model model){
        System.out.println("modify");
        User user = userRespository.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }
}
