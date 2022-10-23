package com.springboot.blog.blog.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Role {

    @Id
    private int id;

    private String name;

}
