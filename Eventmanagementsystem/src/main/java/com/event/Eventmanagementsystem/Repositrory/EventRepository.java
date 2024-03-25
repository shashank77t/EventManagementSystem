package com.event.Eventmanagementsystem.Repositrory;

import com.event.Eventmanagementsystem.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {
    @Query(value = "SELECT * FROM csvfile.dataset as e WHERE e.date BETWEEN ?1 AND DATE_ADD(?1, INTERVAL 14 DAY) ORDER BY e.date, e.time", nativeQuery = true)
    List<Object[]> getData(String date);

    @Query(value = "SELECT * FROM csvfile.event as e ORDER BY e.date",nativeQuery = true)
    List<Event> getAll();
}
