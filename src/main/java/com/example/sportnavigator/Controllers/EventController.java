package com.example.sportnavigator.Controllers;


import com.example.sportnavigator.DTO.EventDTO;
import com.example.sportnavigator.Models.Event;
import com.example.sportnavigator.Service.EventService;
import com.example.sportnavigator.mapper.MapStructMapper;
import com.example.sportnavigator.util.exceptions.EventNotCreatedEcxeption;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;
    private final MapStructMapper mapStructMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid EventDTO eventDTO,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new EventNotCreatedEcxeption(errorMsg.toString());
        }

        eventService.save(mapStructMapper.EnventDTOToEvent(eventDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public EventDTO findById(@PathVariable Long id){
        Event event = eventService.findById(id);

        return mapStructMapper.EventToEventDTO(event);
    }

    @GetMapping
    public List<EventDTO> findAll(){
        List<Event> events = eventService.findAll();
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event: events){
            eventDTOList.add(mapStructMapper.EventToEventDTO(event));
        }

        return  eventDTOList;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){

        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
