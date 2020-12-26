package org.launchcode.bookshop.controllers;

import org.launchcode.bookshop.data.TagRepository;
import org.launchcode.bookshop.models.Event;
import org.launchcode.bookshop.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model){
        model.addAttribute("title","All Tags");
        model.addAttribute("tags",tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayCreateTagForm(Model model){
        model.addAttribute("title","Create Tag");
        model.addAttribute("event",new Event());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag,
                                        Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Tag");
            model.addAttribute("event",new Event());
            return "tags/create";
        }

        tagRepository.save(tag);
        return "redirect:"; //instruct the browser to go to a different form
    }
}
