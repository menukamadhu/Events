package com.example.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class RecordDTO {
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private List<EventSubDTO> event;
}
