package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String name);

    void deleteUser(User user);
}
