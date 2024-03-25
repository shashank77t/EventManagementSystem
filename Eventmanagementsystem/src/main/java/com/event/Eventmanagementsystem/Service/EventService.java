package com.event.Eventmanagementsystem.Service;

import com.event.Eventmanagementsystem.Dto.ResponseDto.*;
import com.event.Eventmanagementsystem.Entity.Event;
import com.event.Eventmanagementsystem.Repositrory.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;




    public EventsResponseDto getData( Double latitude, Double longitude,String date,int page){
        List<Object[]>ans=eventRepository.getData(date);
        EventsResponseDto eventsResponseDto=new EventsResponseDto();
       List<Event>ans2=eventsResponseDto.getEvents();
       for(Object[] row:ans){

           String event_name=(String) row[0];
           String city_name=(String) row[1];
           String date1=(String) row[2];
           String time=(String) row[3];
           Double latitude1=(Double) row[4];
           Double longitude1=(Double) row[5];
           Event e=new Event();
           e.setEvent_name(event_name);
           e.setCity_name(city_name);
           e.setDate(date1);

           String city=e.getCity_name();
           int cl=city.length();
            String temp="";
           for(int k=0;k<cl;k++){
               if(city.charAt(k)==' '){
                  temp+="%20";
               }else{
                   temp+=city.charAt(k);
               }
           }

           String weather="https://gg-backend-assignment.azurewebsites.net/api/Weather?code=KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg==&city="+temp+"&date="+date;
           RestTemplate rest=new RestTemplate();
           HttpHeaders htt=new HttpHeaders();
           HttpEntity entity=new HttpEntity(htt);
           ResponseEntity res=rest.exchange(weather, HttpMethod.GET,entity, WeatherDto.class);
           WeatherDto we=(WeatherDto) res.getBody();
           e.setWeather(we.getWeather());
         //  System.out.println(we.getWeather());
           String uselat=String.valueOf(latitude);
           String uselon=String.valueOf(longitude);
           String deslat=String.valueOf(latitude1);
           String deslon=String.valueOf(longitude1);
           String distance="https://gg-backend-assignment.azurewebsites.net/api/Distance?code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ==&latitude1="+uselat+"&longitude1="+uselon+"&latitude2="+deslat+"&longitude2="+deslon;
           ResponseEntity res2=rest.exchange(distance,HttpMethod.GET,entity, DistanceDto.class);
           DistanceDto dis=(DistanceDto) res2.getBody();
           double value = Double.parseDouble(dis.getDistance());
           e.setDistance_km(value);
           e.setDistance_km(value);
          ans2.add(e);


       }
       eventRepository.saveAll(ans2);
       int size=ans2.size();
       int pagesize=10;
        int totp = (int) Math.ceil((double)ans2.size() / 10);


        if(page-1==totp){
           pagesize=ans2.size()%10;
       }

        if (page-1 > totp) {
            throw new IllegalArgumentException("Invalid page number. Maximum available page: " + totp);
        }
        PageRequest pageRequest = PageRequest.of(page-1,pagesize,Sort.by(Sort.Direction.ASC, "date"));
        //pass it to repos
        Page<Event> pagingUser = eventRepository.findAll(pageRequest);
        //pagingUser.hasContent(); -- to check pages are there or not
        eventsResponseDto.setEvents(pagingUser.getContent());
        eventsResponseDto.setPage(page);
        eventsResponseDto.setPageSize(10);
        eventsResponseDto.setTotalEvents(ans2.size());
        eventsResponseDto.setTotalPages(totp);
       return eventsResponseDto;
    }
}
