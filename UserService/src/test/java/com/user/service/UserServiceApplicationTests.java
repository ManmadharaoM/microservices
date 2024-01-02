package com.user.service;

import com.user.service.entities.Rating;
import com.user.service.external.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class  UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating=Rating.builder().ratingId("10").userId("").hotelId("").rating(5).feedback("crated by feign client").build();
		ResponseEntity<Rating> ratingResponseEntity = ratingService.crateRating(rating);
		System.out.println(ratingResponseEntity);
	}

	@Test
	void deleteRating(){
		ratingService.deleteRating("10f8d808-c50a-4ad2-8637-6142c868f322");
		System.out.println("deleted");
	}

}
