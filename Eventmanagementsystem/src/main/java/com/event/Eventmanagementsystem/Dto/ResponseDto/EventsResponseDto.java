package com.event.Eventmanagementsystem.Dto.ResponseDto;

import com.event.Eventmanagementsystem.Entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EventsResponseDto {
    List<Event> events=new ArrayList<>();
    int page;
    int pageSize;
    int totalEvents;
    int totalPages;
}
