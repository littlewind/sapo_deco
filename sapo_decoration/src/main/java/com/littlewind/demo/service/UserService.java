package com.littlewind.demo.service;

import com.littlewind.demo.model.User;

public interface UserService {
    User save(User user);
    
    User login(User user);
    
    User findByEmail(String email);
    
}
