package com.twitterapi.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    
    private String username;
    private List<TweetDto> tweets;

    public User toUserEntity(){
        User user = new User();

        List<Tweet> tweet = new ArrayList<>();
        for(TweetDto tweetDto : tweets){
            tweet.add(tweetDto.toTweetEntity());
        }
        user.setUsername(username);
        user.setTweets(tweet);

        return user;
    }

    public UserDto() {
        super();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<TweetDto> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetDto> tweets) {
        this.tweets = tweets;
    }

    }
