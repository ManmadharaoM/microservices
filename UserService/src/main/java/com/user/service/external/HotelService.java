package com.user.service.external;

import com.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    //give the same URL what we have given in the hotel controller
    @GetMapping("/hotels/getById/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
