package com.twitterapi.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.twitterapi.persistence.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet,Long>{

    List<Tweet> findTweetByTweetUsername(String tweetUsername);

    List<Tweet> findTweetByTweetTopic(String tweetTopic);

    List<Tweet> findAll();

    Optional<Tweet> findById(Long id);
    
    void deleteById(Long id);
    
}
