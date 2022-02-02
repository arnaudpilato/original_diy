package com.wildcodeschool.original_diy.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DiyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String comment;
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm ")
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private DiyWorkshop diyWorkshop;
    @ManyToOne(fetch = FetchType.LAZY)
    private DiyUser diyUser;

    public DiyComment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public DiyWorkshop getDiyWorkshop() {
        return diyWorkshop;
    }

    public void setDiyWorkshop(DiyWorkshop diyWorkshop) {
        this.diyWorkshop = diyWorkshop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiyUser getDiyUser() {
        return diyUser;
    }

    public void setDiyUser(DiyUser diyUser) {
        this.diyUser = diyUser;
    }

}
