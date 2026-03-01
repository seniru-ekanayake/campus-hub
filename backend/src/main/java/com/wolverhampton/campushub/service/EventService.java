package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.EventDTO;
import com.wolverhampton.campushub.entity.Event;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.EventRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired private EventRepository eventRepository;
    @Autowired private UserRepository userRepository;

    public List<EventDTO> getUpcomingEvents() {
        return eventRepository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EventDTO getEvent(Long id) {
        return toDTO(eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found")));
    }

    public EventDTO createEvent(EventDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event e = new Event();
        mapToEntity(dto, e);
        e.setCreatedBy(user);
        return toDTO(eventRepository.save(e));
    }

    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        mapToEntity(dto, e);
        return toDTO(eventRepository.save(e));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void mapToEntity(EventDTO dto, Event e) {
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setLocation(dto.getLocation());
        e.setCampus(dto.getCampus());
        e.setEventDate(dto.getEventDate());
        e.setCapacity(dto.getCapacity());
        e.setCategory(dto.getCategory());
        e.setImageUrl(dto.getImageUrl());
    }

    public EventDTO toDTO(Event e) {
        EventDTO dto = new EventDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setLocation(e.getLocation());
        dto.setCampus(e.getCampus());
        dto.setEventDate(e.getEventDate());
        dto.setCapacity(e.getCapacity());
        dto.setCategory(e.getCategory());
        dto.setImageUrl(e.getImageUrl());
        dto.setCreatedAt(e.getCreatedAt());
        if (e.getCreatedBy() != null) dto.setCreatedByUsername(e.getCreatedBy().getUsername());
        return dto;
    }
}
