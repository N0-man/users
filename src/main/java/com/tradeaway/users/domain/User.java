package com.tradeaway.users.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by noumanm on 7/15/17.
 */

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String fname;

    @NotNull
    private String lname;

    @NotNull
    private String type;

    public User(String fname, String lname, String type) {
        this.fname = fname;
        this.lname = lname;
        this.type = type;
    }
}