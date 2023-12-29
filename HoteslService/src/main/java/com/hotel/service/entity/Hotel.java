package com.hotel.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
