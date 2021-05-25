package com.moneyview.calendercollab.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    private Long id;
    private String userId;
    private String sourceType;
    private String title;
    private String description;
    private String url;
    private Boolean active;
    private Date scheduledAt;
    private List<Long> invitees;

}
