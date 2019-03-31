package com.cheng.thymeleaf.respository;

import java.util.List;

import com.cheng.thymeleaf.domain.User;

public interface UserRespository {

    User saveOrUpdateUser(User user);
    
    void deleteUser(Long id);
    
    public User getUserById(Long id);
    
    List<User> listUsers();
}
