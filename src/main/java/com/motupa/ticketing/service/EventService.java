package com.motupa.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motupa.ticketing.entity.Event;
import com.motupa.ticketing.exception.EventNotFoundException;
import com.motupa.ticketing.repository.EventRepository;
import com.motupa.ticketing.request.EventRequest;
import com.motupa.ticketing.response.EventResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public List<EventResponse> findAll() {
        return eventRepository.findAll().stream().map(EventResponse::from).toList();
    }

    public EventResponse findById(Long id) {
        return EventResponse.from(getOrthrow(id));
    }

    @Transactional
    public EventResponse create(EventRequest request) {
        Event event = new Event();

        apply(event, request);
        return EventResponse.from(eventRepository.save(event));
    }

    @Transactional
    public EventResponse update(Long id , EventRequest request){
        Event event = getOrthrow(id);
        apply(event, request);
        return EventResponse.from(event);
    }

    @Transactional
    public void delete(Long id) {
        eventRepository.delete(getOrthrow(id));
    }

    private Event getOrthrow(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    private void apply(Event event, EventRequest r) {
        event.setTitle(r.title());
        event.setDescription(r.description());
        event.setVenue(r.venue());
        event.setStartTime(r.startTime());
        event.setCapacity(r.capacity());
        event.setPrice(r.price());
    }

}
