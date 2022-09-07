package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository rRepo;
    RoleServiceImpl(RoleRepository rRepo) {
        this.rRepo = rRepo;
    }
    @Override
    public List<Role> allRoles() {
        return rRepo.findAll();
    }
}
