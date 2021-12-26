package web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping(value = "/user/hello")
    public String printWelcome(ModelMap model, Authentication authentication) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to your home page!");
        messages.add("Your name: " + authentication.getName());
        messages.add("Your role/s: " + authentication.getAuthorities());
        model.addAttribute("messages", messages);
        return "hello-user";
    }
}
