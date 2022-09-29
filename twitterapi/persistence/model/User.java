package com.twitterapi.persistence.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;

    private String password;

    private String email;


    @OneToMany
    @JoinColumn(name = "following_user_id")
    private Set<Follow> followings;

    @OneToMany
    @JoinColumn(name = "followed_user_id")
    private Set<Follow> followers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets;

    public UserDto toUserDto(){
        var userDto = new UserDto();

        List<TweetDto> tweetDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            tweetDtos.add(tweet.toTweetDto());
    }
    userDto.setTweets(tweetDtos);
    userDto.setUsername(username);

    return userDto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Follow> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Follow> followings) {
        this.followings = followings;
    }

    public Set<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follow> followers) {
        this.followers = followers;
    }

}
