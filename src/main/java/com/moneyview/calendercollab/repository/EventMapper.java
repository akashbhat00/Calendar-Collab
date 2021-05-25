package com.moneyview.calendercollab.repository;

import com.moneyview.calendercollab.dto.EventDto;
import com.moneyview.calendercollab.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event eventDtoToEvent(EventDto eventDto);
    EventDto eventToEventDto(Event event);
}
