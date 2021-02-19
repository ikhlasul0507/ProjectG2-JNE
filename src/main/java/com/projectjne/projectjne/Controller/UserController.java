package com.projectjne.projectjne.Controller;

import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Service.UserService;
import com.projectjne.projectjne.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("jne")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    //get all user
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUser() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
    //get user by id
    @RequestMapping(value = "/user/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("idUser") String idUser) {
        logger.info("Fetching user with id User {}", idUser);
        User user = userService.findById(idUser);
        if (user == null) {
            logger.error("user with iduser Harga {} not found .", idUser);
            return new ResponseEntity<>(new CustomErrorType("user with id " + idUser + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    //get all user by name
    @RequestMapping(value = "/user/nama/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByName(@PathVariable("username") String username) {
        logger.info("Fetching user with name {}", username);
        User user = userService.findByNameAll(username);
        if(user == null) {
            logger.error("user with name {} not found.", username);
            return new ResponseEntity<>(new CustomErrorType("user with name " + username + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //insert user
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Creating User  : {} ", user);
        System.out.println("name :"+user.getUsername());
        if(user.getUsername() == ""){
            return new ResponseEntity<>(new CustomErrorType("user name not null"),
                    HttpStatus.NOT_FOUND);
        }else if(user.getEmail() == ""){
            return new ResponseEntity<>(new CustomErrorType("user email not null"),
                    HttpStatus.NOT_FOUND);
        }else if(user.getPassword() == ""){
            return new ResponseEntity<>(new CustomErrorType("user password not null"),
                    HttpStatus.NOT_FOUND);
        }else if (userService.isUserExist(user)) {
            logger.error("Unable to create, user already exist", user.getUsername());
            return new ResponseEntity<>(new CustomErrorType("Unable to create, user already" + user.getUsername()), HttpStatus.CONFLICT);
        }else{
            userService.saveUser(user);
            return new ResponseEntity<>("Data Berhasil Di Simpan", HttpStatus.OK);
        }
    }
    //delete user by id
    @RequestMapping(value = "/user/{idUser}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") String idUser) {
        logger.info("Fetching & Deleting user with id User {}", idUser);
        if(idUser == null){
            return new ResponseEntity<>(new CustomErrorType("id User not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            User user = userService.findById(idUser);
            if (user == null) {
                logger.error("Unable to delete. user with id {} not found.", idUser);
                return new ResponseEntity<>(new CustomErrorType("Unable to delete. user with id user " + idUser + " not found."),
                        HttpStatus.NOT_FOUND);
            } else {
                userService.deleteUserById(idUser);
                return new ResponseEntity<>("Data Berhasil Di Hapus", HttpStatus.OK);
            }
        }
    }
    //update user by id
    @RequestMapping(value = "/user/{idUser}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("idUser") String idUser, @RequestBody User user) {
        logger.info("Updating user with id {}", idUser);
        User currentUser = userService.findById(idUser);
        System.out.println("password"+user.getPassword());
        if (currentUser == null) {
            logger.error("Unable to update. user with id {} not found.", idUser);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. user with id " + idUser + " not found."),
                    HttpStatus.NOT_FOUND);
        }else if(user.getUsername() == ""){
            return new ResponseEntity<>(new CustomErrorType("user name not null"),
                    HttpStatus.NOT_FOUND);
        }else if(user.getEmail() == ""){
            return new ResponseEntity<>(new CustomErrorType("user email not null"),
                    HttpStatus.NOT_FOUND);
        }else if(user.getPassword() == ""){
            return new ResponseEntity<>(new CustomErrorType("user password not null"),
                    HttpStatus.NOT_FOUND);
        }else {
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());

            userService.updateUser(currentUser);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
    }

}

