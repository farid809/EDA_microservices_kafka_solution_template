package io.microservices.solution.template.twitter.to.kafka.service.runner;


import twitter4j.TwitterException;


public interface StreamRunner {

    void start() throws TwitterException;
}