package com.example.TweetConsumer.service;

import com.example.TweetConsumer.domain.Tweet;
import com.example.TweetConsumer.repository.TweetRepository;
import com.example.TweetConsumer.transformer.TweetTransformer;
import com.example.TweetConsumer.validator.ValidateTweets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TwitterConectorService twitterConectorService;

    @Autowired
    ValidateTweets validateTweets;

    public List getAllTweets(){
        List<Tweet> tweets = new ArrayList<>();
        tweetRepository.findAll().forEach(tweets::add);
        return tweets;
    }

    public Tweet getTweetById (String id){
        return tweetRepository.findById(id).orElseGet(Tweet::new);
    }

    public List getAllValidateTweets(){
        List<Tweet> tweets = new ArrayList<>();
        tweetRepository.findByValidation(true).forEach(tweets::add);
        return tweets;
    }

    public List getTopHashtags(){
        List<String> tweets = new ArrayList<>();
        tweetRepository.findAll().forEach(tweet -> {
            tweets.addAll(tweet.getHashtags());
        });
        return tweets
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .map(id -> id.getKey())
                .collect(Collectors.toList())
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public void addTweet (Tweet tweet){
        tweetRepository.save(tweet);
    }

    public void updateTweet (Tweet tweet){
        tweetRepository.save(tweet);
    }

    public void deleteTweet (String id){
        tweetRepository.deleteById(id);
    }

    public void consumeTweets(){

        try{
            ResponseList<Status> statusResponseList = twitterConectorService.getTweets(200);
//            System.out.println(statusResponseList);
            statusResponseList
                    .stream()
                    .filter(tweet -> validateTweets.validateTweet(tweet))
                    .map(status -> TweetTransformer.transformTweet(status))
                    .forEach(this::addTweet);
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void validateTweet(String id){
        tweetRepository.findById(id).ifPresent(tweet -> {
            tweet.setValidation(true);
            tweetRepository.save(tweet);
        });
    }

}
