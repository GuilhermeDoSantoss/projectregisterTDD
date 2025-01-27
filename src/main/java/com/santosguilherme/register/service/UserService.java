package com.santosguilherme.register.service;

import com.santosguilherme.register.domain.User;
import com.santosguilherme.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }


}
