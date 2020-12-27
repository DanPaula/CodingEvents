package org.launchcode.bookshop.data;

import org.launchcode.bookshop.models.Event;
import org.launchcode.bookshop.models.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    //create read update delete
    //there are other repositories that let you do special things
    //allows our code to store and retrieve object instances from a database of a specific type
    //on the fly it will create a class that implements this one and uses eventRepository
    @Transactional
    @Modifying
    @Query("update Event e set e.place = :place where e.name = :name")
    void updateEvents(@Param(value = "place") String place, @Param(value = "name") String name);

    @Query("SELECT e FROM Event e WHERE e.name = :name")
    public Event getEventByName(@Param("name") String name);
}
