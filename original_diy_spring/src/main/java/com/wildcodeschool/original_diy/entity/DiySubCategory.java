package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sub_category")
public class DiySubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private DiyCategory category;

    public DiySubCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiyCategory getCategory() {
        return category;
    }

    public void setCategory(DiyCategory category) {
        this.category = category;
    }
}
