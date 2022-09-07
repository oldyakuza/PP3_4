package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(User user);
    Role saveRole(Role role);
    User getUserById(long id);
    void addRoleToUser(String username, String roleName);
    User updateUser(User user);
    void deleteUser(long id);
    User getUser(String username);
    List<User> allUsers();
}
