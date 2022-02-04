package com.wildcodeschool.original_diy.entity;

import com.wildcodeschool.original_diy.model.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // PIL : Conversion énum eRole en chaine de caractères
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

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
