package com.event.Eventmanagementsystem.Dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Csvdb {
    String event_name;
    String city_name;
    String date;
    String time;
    Double latitude;
    Double longitude;
}
