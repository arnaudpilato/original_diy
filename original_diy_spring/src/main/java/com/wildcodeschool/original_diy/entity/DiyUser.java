package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.*;

/**
 * Pil : Added constraints to make the username and email table unique <br>
 * - The username, email and password are required
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users", uniqueConstraints = {
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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<DiyRole> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "diyUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DiyComment> comments;


    @OneToMany(
            mappedBy = "diyUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("id DESC")
    @JsonIgnore
    private List<DiyWorkshop> workshops;

    @ManyToMany
    @JoinTable(name = "user_badges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    private Set<DiyBadge> badges = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "workshops_reservation_user",
            joinColumns = @JoinColumn(name = "reservation_user_id"),
            inverseJoinColumns = @JoinColumn(name = "diy_workshop_id")
    )
    private List<DiyUser> diyWorkshop;

    private String resetPasswordToken;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm ")
    private Date tokenDate;
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

    public List<DiyComment> getComments() {
        return comments;
    }

    public void setComments(List<DiyComment> comments) {
        this.comments = comments;
    }

    public List<DiyWorkshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<DiyWorkshop> workshops) {
        this.workshops = workshops;
    }

    public Set<DiyBadge> getBadges() { return badges; }

    public void setBadges(Set<DiyBadge> badges) { this.badges = badges; }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public List<DiyUser> getDiyWorkshop() {
        return diyWorkshop;
    }

    public void setDiyWorkshop(List<DiyUser> diyWorkshop) {
        this.diyWorkshop = diyWorkshop;
    }
}
