package org.launchcode.bookshop.controllers;

import org.launchcode.bookshop.data.EventRepository;
import org.launchcode.bookshop.data.UserRepository;
import org.launchcode.bookshop.data.UserRoleRepository;
import org.launchcode.bookshop.models.Event;
import org.launchcode.bookshop.models.EventCategory;
import org.launchcode.bookshop.models.UserRole;
import org.launchcode.bookshop.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("")
public class AdminController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping("createUser")
    public String createUserForm(Model model){
        model.addAttribute("title","Create User");
        model.addAttribute("users",new Users());
        return "userTemp/createUser";
    }

    //lives at /events/create
    //modelAttribute will look in requestData and look for fields of the Event class
    @PostMapping("createUser")
    public String createUser(@ModelAttribute @Valid Users newUser,
                              Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create User");
            return "userTemp/createUser";
        }

        String encodedPassword = passwordEncoder.encode(newUser.getPassword());

        newUser.setEnabled(Boolean.TRUE);
        newUser.setPassword(encodedPassword);
        newUser.setUsername(newUser.getUsername());
        newUser.setUserRole(userRoleRepository.findById(2).get());
        Users userFind = userRepository.getUserByUsername(newUser.getUsername());


        if(userFind==null){
            userRepository.save(newUser);
        }
        else
            return "redirect:";

        return "redirect:";
    }

    @GetMapping("readUser")
    public String displayAllUsers(Model model){
        model.addAttribute("title","All Users");
        model.addAttribute("users", userRepository.findAll());
        return "userTemp/readUser";
    }

    @GetMapping("delete")
    public String displayUsersForm(Model model){
        model.addAttribute("title","Delete Users");
        model.addAttribute("users", userRepository.findAll());
        return "userTemp/delete";
    }

    @PostMapping("delete")
    //required=false lets the method to be called without any parameters
    public String deleteEvent(@RequestParam(required = false) int[] userIds){
        if(userIds!=null){
            for(int id: userIds){
                userRepository.deleteById(id);
            }
        }
        return "redirect: ";
    }
}
