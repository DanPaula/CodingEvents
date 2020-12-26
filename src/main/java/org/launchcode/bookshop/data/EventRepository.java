package org.launchcode.bookshop.data;

import org.launchcode.bookshop.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    //create read update delete
    //there are other repositories that let you do special things
    //allows our code to store and retrieve object instances from a database of a specific type
    //on the fly it will create a class that implements this one and uses eventRepository
}
