package com.wildcodeschool.original_diy.request;

import com.wildcodeschool.original_diy.entity.DiyRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserRequest {
    @NotBlank
    @Size(min = 1, max = 100)
    private String username;

    private String firstName;

    private String lastName;

    private Long phone;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    private Long[] badgesSelected;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long[] getBadgesSelected() { return badgesSelected; }

    public void setBadgesSelected(Long[] badgesSelected) { this.badgesSelected = badgesSelected; }
}
