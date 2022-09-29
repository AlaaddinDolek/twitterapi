package com.twitterapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitterapi.exceptions.TweetIdNotFoundException;
import com.twitterapi.exceptions.TweetUpdateException;
import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.persistence.model.Tweet;
import com.twitterapi.persistence.model.TweetDto;
import com.twitterapi.persistence.model.User;
import com.twitterapi.persistence.repository.TweetRepository;
import com.twitterapi.persistence.repository.UserRepository;

@Service
public class TweetService {

    @Autowired(required = false)
    private TweetRepository tweetRepository;

    @Autowired(required = false)
    private UserRepository userRepository;

    public Long createTweet(TweetDto newTweet)
            throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(newTweet.getTweetUsername());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found !");
        }

        var tweet = new Tweet();
        tweet.setTweetString(newTweet.getTweetString());
        tweet.setTweetUsername(newTweet.getTweetUsername());
        tweet.setTweetTopic(newTweet.getTweetTopic());
        tweet.setTweetDate(new Date());
        tweet.setUser(user.get());

        var savedTweet = tweetRepository.save(tweet);
        return savedTweet.getTweetId();
    }

    public List<TweetDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();

        List<TweetDto> tweetDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            tweetDtos.add(tweet.toTweetDto());
        }
        return tweetDtos;
    }

    public List<TweetDto> findTweetByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        List<Tweet> tweets = tweetRepository.findTweetByTweetUsername(username);
        List<TweetDto> tweetDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            tweetDtos.add(tweet.toTweetDto());
        }
        return tweetDtos;
    }

    public List<TweetDto> findTweetByTweetTopic(String tweetTopic) throws TweetIdNotFoundException {
        List<Tweet> tweets = tweetRepository.findTweetByTweetTopic(tweetTopic);
        if (tweets.isEmpty()) {
            throw new TweetIdNotFoundException("Tweet Topic Not Found!");
        }
        List<TweetDto> tweetDtos = new ArrayList<>();
        for (Tweet tweet : tweets) {
            tweetDtos.add(tweet.toTweetDto());
        }
        return tweetDtos;
    }

    public TweetDto findTweetById(Long id) throws TweetIdNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (!tweetOptional.isPresent()) {
            throw new TweetIdNotFoundException("Tweet Not Found!");
        }
        Tweet tweet = tweetOptional.get();

        return tweet.toTweetDto();
    }

    public TweetDto updateTweetByTweetId(Long id, TweetDto newTweet)
            throws TweetIdNotFoundException, TweetUpdateException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (!tweetOptional.isPresent()) {
            throw new TweetIdNotFoundException("Tweet Not Found!");
        }

        tweetOptional.get().setTweetString(newTweet.getTweetString());

        return tweetOptional.get().toTweetDto();
    }

    public void deleteTweetById(Long id) throws TweetIdNotFoundException {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (!tweetOptional.isPresent()) {
            throw new TweetIdNotFoundException("Tweet Not Found!");
        }
        tweetRepository.deleteById(id);
    }
}
