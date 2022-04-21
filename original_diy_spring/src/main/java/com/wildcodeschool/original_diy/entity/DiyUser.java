package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Pil : Added constraints to make the username and email table unique <br>
 * - The username, email and password are required
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
public class DiyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private Long phone;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<DiyRole> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DiyComment> diyComments = new ArrayList<>();


    @OneToMany(
            mappedBy = "diyUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("id DESC")
    @JsonIgnore
    private List<DiyWorkshop> workshops;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_badges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    @JsonManagedReference("badges")
    private Set<DiyBadge> badges;

    public DiyUser() {
    }

    public DiyUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<DiyRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<DiyRole> roles) {
        this.roles = roles;
    }

    public List<DiyComment> getDiyComments() {
        return diyComments;
    }

    public void setDiyComments(List<DiyComment> diyComments) {
        this.diyComments = diyComments;
    }

    public List<DiyWorkshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<DiyWorkshop> workshops) {
        this.workshops = workshops;
    }

    public Set<DiyBadge> getBadges() { return badges; }

    public void setBadges(Set<DiyBadge> badges) { this.badges = badges; }
}
