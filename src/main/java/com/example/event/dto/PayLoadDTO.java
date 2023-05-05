package com.example.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class PayLoadDTO {
    private UUID batchId;
    private List<RecordDTO> records;
}
