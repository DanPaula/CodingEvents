package org.launchcode.bookshop.controllers;

import org.launchcode.bookshop.data.EventCategoryRepository;
import org.launchcode.bookshop.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryControllers {
    //autowired - spring boot should populate this field (dependency injection)
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    //findAll, save, findById

    @GetMapping("listCategories")
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "eventCategories/listCategories";
    }

    //lives at /eventCategories/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    //lives at /events/create
    //modelAttribute will look in requestData and look for fields of the Event class
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid EventCategory newEventCategory,
                              Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:"; //instruct the browser to go to a different form
    }

}
