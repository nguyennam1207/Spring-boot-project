package com.example.spring_boot_project.entity;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.Set;
>>>>>>> 6aed02d (final update)

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
<<<<<<< HEAD

@Entity
@Table(name = "users")
public class Users {

=======
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "users")
public class Users {

    public Users() {
    }

>>>>>>> 6aed02d (final update)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;
<<<<<<< HEAD
    @Column(name = "password")
    private String passWord;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "dob")
    private LocalDate dob;

    public Users(int id, String userName, String passWord, String firstName, String lastName, LocalDate dob) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Users() {
=======

    @Column(name = "password")
    private String passWord;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "role")
    private Set<String> role;

    @Builder
    public Users(LocalDate dob, String firstName, int id, String lastName, String passWord, Set<String> role, String userName) {
        this.dob = dob;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.passWord = passWord;
        this.role = role;
        this.userName = userName;
>>>>>>> 6aed02d (final update)
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

<<<<<<< HEAD
=======
    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

>>>>>>> 6aed02d (final update)
}
