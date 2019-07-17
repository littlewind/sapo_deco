package com.littlewind.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littlewind.demo.model.TestModel;
import com.littlewind.demo.model.User;
import com.littlewind.demo.model.UserLite;
import com.littlewind.demo.service.UserService;


@RestController
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    
    @PostMapping("/registration")
    public UserLite registration(@RequestBody User userForm) {
        User new_user = userService.save(userForm);
        UserLite new_user_lite = new UserLite(new_user);
        return new_user_lite;
    }
    
    @PostMapping("/login")
    public UserLite login(@RequestBody User userForm) {
    	User user = userService.login(userForm);
        UserLite user_lite = new UserLite(user);
        return user_lite;
    }



    @GetMapping("/welcome")
    public HashMap<String, String> welcome() {
    	HashMap<String, String> hashMap = new HashMap<>();
    	hashMap.put("message", "Hello World");
        return hashMap;
    }
    
    @GetMapping("/hello")
    public HashMap<String, List<TestModel>> hello() {
        TestModel prod = new TestModel("prod1", "cate1");
        List<TestModel> list = new ArrayList<>();
        list.add(prod);
        HashMap<String, List<TestModel>> to_return = new HashMap<>();
        to_return.put("funct", list);
        return to_return;
    }
}
