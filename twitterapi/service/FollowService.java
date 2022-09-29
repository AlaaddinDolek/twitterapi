package com.twitterapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.twitterapi.exceptions.UsernameNotFoundException;
import com.twitterapi.persistence.model.Follow;
import com.twitterapi.persistence.repository.FollowRepository;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Long follow(Long followingUserId, Long followedUserId){
        Follow follow = new Follow();
        follow.setFollowedUserId(followedUserId);
        follow.setFollowingUserId(followingUserId);

        return followRepository.save(follow).getId();
    }

    public void unFollowByFollowId(Long id) throws UsernameNotFoundException{
        Optional<Follow> follow = followRepository.findById(id);
        if(follow.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        followRepository.deleteById(id);
    }

   
}
