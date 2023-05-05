package com.example.event.service;

import com.example.event.dto.EventSubDTO;
import com.example.event.dto.EventsDTO;
import com.example.event.dto.PayLoadDTO;
import com.example.event.dto.RecordDTO;
import com.example.event.entity.Events;
import com.example.event.repository.EventsRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventsService {
    @Autowired
    private EventsRepo eventsRepo;
    @Autowired
    private ModelMapper modelMapper;

    public EventsDTO addEvent(EventsDTO eventsDTO) {
        Events events = modelMapper.map(eventsDTO, Events.class);
        Events e = eventsRepo.save(events);
        return modelMapper.map(e, EventsDTO.class);
    }

    public List<EventsDTO> getAllEvents() {
        List<Events> list = eventsRepo.findAll();
        return list.isEmpty() ? null : modelMapper.map(list, (new TypeToken<List<EventsDTO>>() {}).getType());
    }

    public EventsDTO getEventById(UUID eventId) {
        Optional<Events> events = this.eventsRepo.findById(eventId);
        return events.isEmpty() ? null : modelMapper.map(events, EventsDTO.class);
    }

    public String updateEvent(EventsDTO eventsDTO) {
        Events events = modelMapper.map(eventsDTO, Events.class);
        this.eventsRepo.save(events);
        return "Event updated successfully";
    }

    public String deleteEvent(UUID eventId) {
        this.eventsRepo.deleteById(eventId);
        return "Event Deleted successfully";
    }

    public PayLoadDTO saveEvent(PayLoadDTO payloadDTO) {
        Iterator var2 = payloadDTO.getRecords().iterator();

        while(var2.hasNext()) {
            RecordDTO recordDTO = (RecordDTO)var2.next();
            String transId = recordDTO.getTransId();
            String transTms = recordDTO.getTransTms();
            String rcNum = recordDTO.getRcNum();
            String clientId = recordDTO.getClientId();
            Iterator var8 = recordDTO.getEvent().iterator();

            while(var8.hasNext()) {
                EventSubDTO eventSubDTO = (EventSubDTO)var8.next();
                Events newEvent = new Events();
                newEvent.setEventId(UUID.randomUUID());
                newEvent.setTransId(transId);
                newEvent.setTransTms(transTms);
                newEvent.setRcNum(rcNum);
                newEvent.setClientId(clientId);
                newEvent.setEventCnt(eventSubDTO.getEventCnt());
                newEvent.setLocationCd(eventSubDTO.getLocationCd());
                newEvent.setLocationId1(eventSubDTO.getLocationId1());
                newEvent.setLocationId2(eventSubDTO.getLocationId2());
                newEvent.setAddrNbr(eventSubDTO.getAddrNbr());
                this.eventsRepo.save(newEvent);
            }
        }

        return payloadDTO;
    }
}
