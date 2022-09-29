package com.twitterapi.persistence.model;

public class TweetDto {

    private String tweetUsername;
    private String tweetString;
    private String tweetTopic;

    public Tweet toTweetEntity() {
        Tweet tweet = new Tweet();
        tweet.setTweetUsername(tweetUsername);
        tweet.setTweetString(tweetString);
        tweet.setTweetTopic(tweetTopic);
        return tweet;
    }

    public TweetDto() {
        super();
    }

    public String getTweetString() {
        return tweetString;
    }

    public void setTweetString(String tweetString) {
        this.tweetString = tweetString;
    }

    public String getTweetTopic() {
        return tweetTopic;
    }

    public void setTweetTopic(String tweetTopic) {
        this.tweetTopic = tweetTopic;
    }

    public String getTweetUsername() {
        return tweetUsername;
    }

    public void setTweetUsername(String tweetUsername) {
        this.tweetUsername = tweetUsername;
    }

}
