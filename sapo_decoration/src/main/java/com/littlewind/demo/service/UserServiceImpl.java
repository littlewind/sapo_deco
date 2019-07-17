package com.littlewind.demo.service;

import java.util.HashSet;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.littlewind.demo.model.User;
import com.littlewind.demo.repository.RoleRepository;
import com.littlewind.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;


    @Override
    public User save(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
//        user.setShop(new HashSet<>(roleRepository.findAll()));
        if (findByEmail(user.getEmail()) != null) {
        	user.setSuccess(0);
        	return user;
        }
        userRepository.save(user);
        User returned_user = findByEmail(user.getEmail());
        returned_user.setSuccess(1);
        return returned_user;
    }
    
    @Override
    public User login(User user) {
    	String email = user.getEmail();
    	String password = user.getPassword();
    	User storedUser= userRepository.findByEmail(email);
    	if (storedUser != null) {
    		boolean valid = BCrypt.checkpw(password, storedUser.getPassword());
    		if (valid) {
    			storedUser.setSuccess(1);
    			return storedUser;
    		}
    	}
    	user.setSuccess(0);
    	return user;
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
