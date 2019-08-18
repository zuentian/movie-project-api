package com.zuer.mjuser.service.impl;

import com.zuer.mjuser.entity.User;
import com.zuer.mjuser.repository.UserRepository;
import com.zuer.mjuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name,password);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public void writeOff(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

}
