package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.UserDto;
import web.service.ClientServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") UserDto user) {
        clientService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", clientService.getAllUsers());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", clientService.editUser(id));
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") UserDto user) {
        clientService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        clientService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
