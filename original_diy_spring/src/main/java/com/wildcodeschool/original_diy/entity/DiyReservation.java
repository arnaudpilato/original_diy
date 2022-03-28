package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "reservation")
public class DiyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private DiyUser user;

    @NotNull
    @ManyToOne
    private DiyWorkshop workshop;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm ")
    private Date date;

    public DiyReservation() {
    }

    public DiyUser getUser() {
        return user;
    }

    public void setUser(DiyUser user) {
        this.user = user;
    }

    public DiyWorkshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(DiyWorkshop workshop) {
        this.workshop = workshop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
