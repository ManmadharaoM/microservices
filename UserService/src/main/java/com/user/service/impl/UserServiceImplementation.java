package com.user.service.impl;


import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given Id not found in server: " + userId));

        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);
        log.info("{}", ratingOfUser);

        List<Rating> ratings = Arrays.asList(ratingOfUser);

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // http://localhost:9092/hotels/getById/8b77c1c5-3ae3-4490-bd28-b13cabc07802
            try {
//                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getById/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = hotelService.getHotel(rating.getHotelId());

//                Hotel hotel = forEntity.getBody();
//                log.info("response status code: {}", forEntity.getStatusCode());
                rating.setHotel(hotel);
            } catch (HttpClientErrorException.NotFound ex) {
                // Handle 404 error - hotel information not found
                log.warn("Hotel information not found for ID: {}", rating.getHotelId());
                // You might want to set a default or handle it based on your application's requirements
                rating.setHotel(new Hotel());
            }
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingList);
        return user;
    }

}