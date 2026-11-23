package com.motupa.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motupa.ticketing.entity.Event;

public interface EventRepository extends JpaRepository<Event , Long> {

}
