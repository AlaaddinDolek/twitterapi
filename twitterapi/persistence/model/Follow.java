package com.twitterapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "following_user_id")
    private Long followingUserId;
    @Column(name = "followed_user_id")
    private Long followedUserId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFollowingUserId() {
        return followingUserId;
    }
    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }
    public Long getFollowedUserId() {
        return followedUserId;
    }
    public void setFollowedUserId(Long followedUserId) {
        this.followedUserId = followedUserId;
    }

    
}
