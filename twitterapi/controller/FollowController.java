package com.twitterapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {
    
    private final FollowService followService;


    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping
    public Long follow(@RequestParam Long followingUserId, @RequestParam Long followedUserId){
        return followService.follow(followingUserId, followedUserId);
    }

    @DeleteMapping
    public void unFollow(@RequestParam Long followId) throws UsernameNotFoundException {
        followService.unFollowByFollowId(followId);
    }

}
