package com.example.TweetConsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

@Service
public class TwitterConectorService {

    private Twitter twitter;

    @Value("${ConsumerKey}")
    private String consumerKey;

    @Value("${ConsumerKeySecret}")
    private String consumerKeySecret;

    @Value("${AccessToken}")
    private String accessToken;

    @Value("${AccessTokenSecret}")
    private String accessTokenSecret;

    private Paging paging;

    private boolean initializeConnection = true;

    public TwitterConectorService() {
        twitter = new TwitterFactory().getInstance();
        paging = new Paging();
        paging.setCount(20);
    }

    private void setUpConnection(){
        twitter.setOAuthConsumer(consumerKey,consumerKeySecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken,accessTokenSecret));
        initializeConnection = false;
    }

    public Twitter getConnection(){
        if(initializeConnection){
            setUpConnection();
        }
        return twitter;
    }


    public ResponseList<Status> getTweets(int number) throws TwitterException {
        paging.setCount(number);
        return getConnection().getHomeTimeline(paging);
    }
}
