package com.example.TweetConsumer.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.*;

@Component
public class ValidateTweets {
    @Value("${NumberOfFollowers}")
    private String numberOfFollowers;

    @Value("${AllowedLanguages}")
    private String allowedLanguages;

    private Set<String> languagesSet = new HashSet<>();

    public boolean validateTweet(Status status){
        SetLanguages();
        if (status.getUser().getFollowersCount() < Integer.valueOf(numberOfFollowers)){
            return false;
        }
        if (!languagesSet.contains(status.getLang())){
            return false;
        }
        return true;
    }

    private void SetLanguages(){
        if (languagesSet.isEmpty()) {
           languagesSet = new HashSet<>(Arrays.asList(allowedLanguages.split(",")));
        }
    }
}
