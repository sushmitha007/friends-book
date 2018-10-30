package com.stackroute.friendsBook.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@NodeEntity
public class User {

    @GraphId
    private Long id;
    private String name;
    private String email;
    private String address;
    private String contactNo;
    private String userProfileImageUrl;
    @Relationship(type = "friend", direction = Relationship.UNDIRECTED)
    private Set<Long> friends;

    public User(Long id, String name, String email, String address, String contactNo, Set<Long> friends, String userProfileImageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNo = contactNo;
        this.friends = friends;
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Set<Long> getFriends() {
        return friends;
    }

    public void setFriends(Set<Long> friends) {
        this.friends = friends;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

}