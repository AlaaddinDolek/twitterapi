package com.twitterapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitterapi.exceptions.TweetIdNotFoundException;
import com.twitterapi.exceptions.TweetUpdateException;
import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.persistence.model.TweetDto;
import com.twitterapi.service.TweetService;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    public Long createTweet(@Valid @RequestBody TweetDto newTweet)
            throws UsernameNotFoundException {
        return tweetService.createTweet(newTweet);
    }

    @DeleteMapping
    public void deleteTweetById(@RequestParam Long id) throws TweetIdNotFoundException {
        tweetService.deleteTweetById(id);
    }

    @PutMapping("/info/{id}")
    public TweetDto updateTweetByTweetId(@PathVariable("id") Long id, @RequestBody TweetDto newTweet)
            throws TweetIdNotFoundException, TweetUpdateException {

        return tweetService.updateTweetByTweetId(id, newTweet);
    }

    @GetMapping("/info/by-topic")
    public List<TweetDto> findTweetByTweetTopic(String tweetTopic) throws TweetIdNotFoundException {
        return tweetService.findTweetByTweetTopic(tweetTopic);
    }

    @GetMapping("/info/{id}")
    public TweetDto findTweetById(@PathVariable("id") Long id) throws TweetIdNotFoundException {
        return tweetService.findTweetById(id);
    }

    @GetMapping
    public List<TweetDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/info/by-username")
    public List<TweetDto> findTweetByUsername(String username) throws UsernameNotFoundException {
        return tweetService.findTweetByUsername(username);
    }

}