package com.event.Eventmanagementsystem.Controller;

import com.event.Eventmanagementsystem.Dto.GeneralMessageDTO;
import com.event.Eventmanagementsystem.Dto.ResponseDto.EventsResponseDto;
import com.event.Eventmanagementsystem.Entity.Event;
import com.event.Eventmanagementsystem.Service.EventService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;
     @ApiResponses({@ApiResponse(responseCode = "201",description = "This Method is invoked by User to find the events within his location ",content = {
             @Content(schema = @Schema(implementation = EventsResponseDto.class),mediaType="Application/json")
     })})
    @GetMapping("/find")
    public ResponseEntity<EventsResponseDto> getData(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String date,@RequestParam(defaultValue = "1") int page){
        try {
            EventsResponseDto responseDto = eventService.getData(latitude, longitude, date, page);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
