package com.projectjne.projectjne.Repository;
import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Model.User;

import java.util.List;

public interface UserRepository {
    User findById(String idUser);
    List<User> findByName(String username);
    List<User> findByNameAll(String username);
    List<User> findAll();
    void saveUser(User user);
    void deleteUserById(String idUser);
    void updateUser(User user);
}
