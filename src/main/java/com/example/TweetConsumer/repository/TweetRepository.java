package com.example.TweetConsumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.TweetConsumer.domain.Tweet;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet,String>  {

    List<Tweet> findByValidation(boolean validation);
}
