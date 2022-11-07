package io.microservices.solution.template.twitter.to.kafka.service;


import io.microservices.solution.template.twitter.to.kafka.service.config.TwitterKafkaServiceConfigData;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
    private final TwitterKafkaServiceConfigData twitterKafkaServiceConfigData;

    public TwitterToKafkaServiceApplication(TwitterKafkaServiceConfigData configData)
    {
        this.twitterKafkaServiceConfigData=configData;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts...");
        LOG.info(Arrays.toString(twitterKafkaServiceConfigData.getTwitterKeywords().toArray(new String[] {})));
        LOG.info(twitterKafkaServiceConfigData.getWelcomeMessage());
    }
}
