package com.twitterapi.persistence.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue
    private Long tweetId;
    private String tweetUsername;
    private String tweetString;
    private Date tweetDate;
    private String tweetTopic;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

  
    public TweetDto toTweetDto() {
        var tweetDto = new TweetDto();
        tweetDto.setTweetUsername(tweetUsername);
        tweetDto.setTweetString(tweetString);
        tweetDto.setTweetTopic(tweetTopic);
        return tweetDto;
    }

    public Tweet() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTweetUsername() {
        return tweetUsername;
    }

    public void setTweetUsername(String tweetUsername) {
        this.tweetUsername = tweetUsername;
    }

    public String getTweetString() {
        return tweetString;
    }

    public void setTweetString(String tweetString) {
        this.tweetString = tweetString;
    }

    public Date getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    public String getTweetTopic() {
        return tweetTopic;
    }

    public void setTweetTopic(String tweetTopic) {
        this.tweetTopic = tweetTopic;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

   

}
