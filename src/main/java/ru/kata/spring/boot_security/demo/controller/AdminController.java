package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String viewAdminPage(Principal principal, Model model) {
        model.addAttribute("currentUser", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/createNewUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}/updateUser")
    public String showUpdatedUser(@PathVariable("id") int id, User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
