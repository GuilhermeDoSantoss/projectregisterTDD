package com.santosguilherme.register.service;

import com.santosguilherme.register.domain.User;
import com.santosguilherme.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user){

        var age = Period.between(user.getDataOfBirth(), LocalDate.now()).getYears();

        if(age < 18 || age > 60){
            throw new RuntimeException("Idade não permitida");
        }

        return userRepository.save(user);
    }


}
