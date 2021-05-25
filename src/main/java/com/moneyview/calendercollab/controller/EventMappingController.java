package com.moneyview.calendercollab.controller;

import com.moneyview.calendercollab.dto.InvitationResponseDto;
import com.moneyview.calendercollab.exception.ResourceNotFoundException;
import com.moneyview.calendercollab.model.Event;
import com.moneyview.calendercollab.model.EventMapping;
import com.moneyview.calendercollab.model.User;
import com.moneyview.calendercollab.repository.EventMappingRepository;
import com.moneyview.calendercollab.repository.EventRepository;
import com.moneyview.calendercollab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventmapping")

public class EventMappingController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventMappingRepository eventMappingRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/{userid}")
    public List<EventMapping> getEventsByUserId(@PathVariable(value = "userid") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//        System.out.println("print res: "+user);
         List<EventMapping> eventMappings = eventMappingRepository.findByUser(user);
         return eventMappings;
    }

    @GetMapping("/getuserschedulebydate/{userid}")
    public ResponseEntity<List<EventMapping>> getEventsByUserId(@PathVariable(value = "userid") Long userId, @RequestParam(value =  "fromdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam(value =  "todate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//        System.out.println("print res: "+user);
        System.out.println("print res: "+user);
        System.out.println("print res: "+fromDate.toString());
        List<EventMapping> eventMappings = eventMappingRepository.findByUserAndScheduledAtBetween(user, fromDate, toDate);
        System.out.println("print res: "+eventMappings);
        return ResponseEntity.ok().body(eventMappings);
    }

    @PutMapping("/respondtoinvitation")
    public ResponseEntity<EventMapping> respondToInvitation(@Valid @RequestBody InvitationResponseDto invitationResponseDto) {
        Optional<User> user = userRepository.findById(invitationResponseDto.getUserId());
        Optional<Event> event = eventRepository.findById(invitationResponseDto.getEventId());
        EventMapping eventMapping = null;
        if(user.isPresent() && event.isPresent()) {
            eventMapping = eventMappingRepository.findByUserAndEvent(user, event);
            eventMapping.setResponseStatus(invitationResponseDto.getResponse());
            eventMappingRepository.save(eventMapping);
        }
        return ResponseEntity.ok().body(eventMapping);
    }
}
