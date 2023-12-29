package com.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    @Column(name = "NAME",length = 20)
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Rating> rating=new ArrayList<>();
}
