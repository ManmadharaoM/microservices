package com.hotel.service.serice;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String hotelId);
}
