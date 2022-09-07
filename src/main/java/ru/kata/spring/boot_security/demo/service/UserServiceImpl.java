package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository uRepo;
    private final RoleRepository rRepo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    UserServiceImpl(UserRepository uRepo, RoleRepository rRepo) {
        this.uRepo = uRepo;
        this.rRepo = rRepo;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return uRepo.save(user);
    }

    @Transactional
    @Override
    public Role saveRole(Role role) {
        return rRepo.save(role);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return uRepo.saveAndFlush(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        uRepo.deleteUserById(id);
    }

    @Transactional
    @Override
    public void addRoleToUser(String username, String roleName) throws UsernameNotFoundException {
        User user = uRepo.findByUsername(username);
        Role role = rRepo.findByName(roleName);
        user.addRole(role);
    }

    @Override
    public User getUser(String username) throws UsernameNotFoundException {
        return uRepo.findByUsername(username);
    }

    @Override
    public User getUserById(long id) {
        return uRepo.getUserById(id);
    }

    @Override
    public List<User> allUsers() {
        return uRepo.findAll();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return uRepo.findByUsername(username);
    }
}
