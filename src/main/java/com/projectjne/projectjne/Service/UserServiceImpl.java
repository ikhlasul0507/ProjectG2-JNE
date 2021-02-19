package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
    public User findById(String idUser) {
        User dh;
        try{
            dh = userRepository.findById(idUser);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }
    @Override
    public User findByNameAll(String username) {
        return (User) userRepository.findByNameAll(username).get(0);
    }
    @Override
    public User findByName(String username) {
        return (User) userRepository.findByName(username).get(0);
    }

    public void saveUser(User user) {
        synchronized (this) {
            userRepository.saveUser(user);
        }
    }

    public void updateUser(User user) {
        synchronized (this) {
            userRepository.updateUser(user);
        }
    }

    public void deleteUserById(String idUser) {
        synchronized (this) {
            userRepository.deleteUserById(idUser);
        }
    }
    public boolean isUserExist(User user){
        return userRepository.findByName(user.getUsername()).size() != 0;
    }
    
}


