package org.launchcode.bookshop.controllers;

import org.launchcode.bookshop.data.EventCategoryRepository;
import org.launchcode.bookshop.data.EventRepository;
import org.launchcode.bookshop.data.TagRepository;
import org.launchcode.bookshop.models.Event;
import org.launchcode.bookshop.models.EventCategory;
import org.launchcode.bookshop.models.Tag;
import org.launchcode.bookshop.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("events")
public class EventControllers {

    //autowired - spring boot should populate this field (dependency injection)
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    //findAll, save, findById

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId,Model model){
        if(categoryId==null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        }else{
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()){
                model.addAttribute("title","Invalid category ID: "+categoryId);
            }
            else{
                EventCategory category = result.get();
                model.addAttribute("title","Events in category: "+category.getName());
                model.addAttribute("events",category.getEvents());
            }
        }
        return "events/index";
    }

    //lives at /events/create
    //displayCreateEventForm
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/create";
    }

    //lives at /events/create
    //modelAttribute will look in requestData and look for fields of the Event class
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent,
                              Errors errors,Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:"; //instruct the browser to go to a different form
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    //required=false lets the method to be called without any parameters
    public String deleteEvent(@RequestParam(required = false) int[] eventIds){
        if(eventIds!=null){
            for(int id: eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId,Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        if(result.isEmpty()){
            model.addAttribute("title","Invalid Event ID: "+eventId);
        }else{
            Event event = result.get();
            model.addAttribute("title",event.getName()+"Details");
            model.addAttribute("event",event);
            model.addAttribute("tags",event.getTags());
        }
        return "events/detail";
    }

    //responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId,Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title","Add Tag to: "+event.getName());
        model.addAttribute("tags",tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag",eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processingAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                       Errors errors,
                                       Model model){
        if (!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId="+event.getId();
        }
        return "redirect:add-tag";
    }

}
