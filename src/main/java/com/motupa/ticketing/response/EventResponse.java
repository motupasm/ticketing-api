package com.motupa.ticketing.response;

import java.math.BigDecimal;
import java.time.Instant;

import com.motupa.ticketing.entity.Event;

public record EventResponse(Long id, String title, String description, String venue, Instant startTime, int capacity,
        BigDecimal price) {

            public static EventResponse from(Event e){
                return  new EventResponse(e.getId(), e.getTitle(), e.getDescription(), e.getVenue(), e.getStartTime() , e.getCapacity() , e.getPrice());
            }
}


