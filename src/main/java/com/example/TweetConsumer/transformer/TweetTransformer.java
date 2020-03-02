package com.example.TweetConsumer.transformer;

import com.example.TweetConsumer.domain.Tweet;
import twitter4j.Status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TweetTransformer {

    public static Tweet transformTweet(Status status){
        Tweet tweet = new Tweet();
        tweet.setId(String.valueOf(status.getId()));
        tweet.setTexto(status.getText());
        tweet.setValidation(false);
        tweet.setUsuario(status.getUser().getScreenName());
        tweet.setLocalization(status.getUser().getLocation());
        List<String> hashtags = Arrays.asList(status.getHashtagEntities())
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
        tweet.setHashtags(hashtags);
        return tweet;
    }
}
