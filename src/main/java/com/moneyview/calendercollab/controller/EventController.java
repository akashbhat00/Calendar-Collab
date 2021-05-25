package com.moneyview.calendercollab.controller;

import com.moneyview.calendercollab.dto.EventDto;
import com.moneyview.calendercollab.exception.ResourceNotFoundException;
import com.moneyview.calendercollab.model.Event;
import com.moneyview.calendercollab.model.EventMapping;
import com.moneyview.calendercollab.model.ResponseStatus;
import com.moneyview.calendercollab.model.User;
import com.moneyview.calendercollab.repository.EventMapper;
import com.moneyview.calendercollab.repository.EventMappingRepository;
import com.moneyview.calendercollab.repository.EventRepository;
import com.moneyview.calendercollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventMappingRepository eventMappingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventMapper eventMapper;


    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
        System.out.println("print res: "+event);
        return event;
    }

    @PostMapping
    public Event createEvent(@Valid @RequestBody EventDto eventDto) {
        System.out.println(eventDto.toString());
        Event event = eventRepository.save(eventMapper.eventDtoToEvent(eventDto));
        System.out.println(event.toString());
        if(eventDto.getInvitees() != null) {
            eventDto.getInvitees().forEach(invitee -> {
                System.out.println("invitee:" + invitee);
                Optional<User> user = userRepository.findById(invitee);
                if (user.isPresent()) {
                    EventMapping eventMapping = new EventMapping();
                    eventMapping.setEvent(event);
                    eventMapping.setUser(user.get());
                    eventMapping.setResponseStatus(ResponseStatus.pending);
                    eventMapping.setScheduledAt(event.getScheduledAt());
                    System.out.println(eventMapping.toString());
                    eventMappingRepository.save(eventMapping);
                }
            });
        }
        System.out.println("event: "+event.toString());
        return event;
    }
}
