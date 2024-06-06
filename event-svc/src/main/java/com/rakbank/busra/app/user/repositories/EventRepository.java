package com.rakbank.busra.app.user.repositories;


import com.rakbank.busra.app.user.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.name LIKE %:text% OR e.description LIKE %:text%")
    List<Event> searchText(@Param("text") String text);

}
