package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Model.User;
import com.projectjne.projectjne.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate connect;

    public List<User> findAll() {
        return connect.query("select * from tbl_user",
                (rs, rowNum) ->
                        new User(
                                rs.getString("idUser"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    // Add new customer
    public void saveUser(User user) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String username = user.getUsername();
        connect.update("INSERT INTO tbl_user (idUser,username,email,password) VALUES (?,?,?,?)",
                randomUUIDString, username, user.getEmail(), username);
    }

    // update new customer
    public void updateUser(User user) {
        connect.update("UPDATE tbl_user SET username= ?,email=?,password=? Where idUser=?",
                user.getUsername(), user.getEmail(), user.getPassword(), user.getIdUser());
    }

    public User findById(String idUser) {
        String sql = "select * from tbl_user WHERE idUser='" + idUser + "'";
        return connect.queryForObject(sql,
                (rs, rowNum) ->
                        new User(
                                rs.getString("idUser"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    public List<User> findByNameAll(String username) {

        return connect.query("Select * FROM tbl_user where username like ?",
                new Object[]{"%" + username + "%"},
                (rs, rowNum) ->
                        new User(
                                rs.getString("idUser"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    public List<User> findByName(String username) {
        return connect.query("Select * FROM tbl_user where username like ?",
                new Object[]{"%" + username + "%"},
                (rs, rowNum) ->
                        new User(
                                rs.getString("idUser"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
        );
    }

    public void deleteUserById(String idUser) {
        connect.execute(" DELETE FROM tbl_user WHERE idUser='" + idUser + "'");
    }
}
