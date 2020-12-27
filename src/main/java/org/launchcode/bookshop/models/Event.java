package org.launchcode.bookshop.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity{
    //models collects large amounts of data more cleanly

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Place is required")
    @Size(min = 3, max = 50, message = "Place must be between 3 and 50 characters")
    private String place;

    //private EventType type;
    @ManyToOne //one event category
    @NotNull(message = "category is required")
    private EventCategory eventCategory;

    @OneToOne(cascade = CascadeType.ALL) //tell hibernate to cascade in case we delete
    @Valid
    @NotNull
    private EventDetails eventDetails;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event(@NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name, @NotBlank(message = "Place is required") @Size(min = 3, max = 50, message = "Place must be between 3 and 50 characters") String place, @NotNull(message = "category is required") EventCategory eventCategory) {
        this.name = name;
        this.place = place;
        this.eventCategory = eventCategory;
    }

    public Event(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
