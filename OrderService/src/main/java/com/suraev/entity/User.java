package com.suraev.entity;

import com.suraev.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private UserType type;

}