package com.twitterapi.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.twitterapi.persistence.model.Follow;

public interface FollowRepository extends CrudRepository<Follow,Long>{ 
    
void deleteById(Long id);
}
