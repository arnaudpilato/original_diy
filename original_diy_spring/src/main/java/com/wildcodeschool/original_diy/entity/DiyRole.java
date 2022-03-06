package com.wildcodeschool.original_diy.entity;

import com.wildcodeschool.original_diy.model.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class DiyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // PIL : Conversion eRole en string
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public DiyRole() { }

    public DiyRole(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
