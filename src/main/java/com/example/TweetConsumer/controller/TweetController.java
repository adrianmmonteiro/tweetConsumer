package com.example.TweetConsumer.controller;

import com.example.TweetConsumer.domain.Tweet;
import com.example.TweetConsumer.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping("/tweets")
    public List getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweets/{id}")
    public Tweet getTweet(@PathVariable String id){
        return tweetService.getTweetById(id);
    }

    @PostMapping("/tweets")
    public void addTweet(@RequestBody Tweet tweet) {
        tweetService.addTweet(tweet);
    }

    @GetMapping("/tweets/validated")
    public List getAllValidatedTweets(){
        return tweetService.getAllValidateTweets();
    }

    @PostMapping("/tweets/validated/{id}")
    public void validatedTweet(@PathVariable String id){
        tweetService.validateTweet(id);
    }

    @GetMapping("/tweets/tags")
    public List getAllHashtags(){
        return tweetService.getTopHashtags();
    }

    @GetMapping("/tweets/consume")
    public void consumeTweets(){
         tweetService.consumeTweets();
    }

}
