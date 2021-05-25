package com.moneyview.calendercollab.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneyview.calendercollab.model.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvitationResponseDto {

    private Long userId;
    private Long eventId;
    private ResponseStatus response;
}
