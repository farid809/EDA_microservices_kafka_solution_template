package io.microservices.solution.template.twitter.to.kafka.service.listener;



import io.microservices.solution.template.config.KafkaConfigData;
import io.microservices.solution.template.kafka.avro.model.TwitterAvroModel;
import io.microservices.solution.template.kafka.producer.service.KafkaProducer;
import io.microservices.solution.template.twitter.to.kafka.service.transformer.TwitterStatusToAvroTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);

    private final KafkaConfigData kafkaConfigData;

    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;

    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    public TwitterKafkaStatusListener(KafkaConfigData configData,
                                      KafkaProducer<Long, TwitterAvroModel> producer,
                                      TwitterStatusToAvroTransformer transformer) {
        this.kafkaConfigData = configData;
        this.kafkaProducer = producer;
        this.twitterStatusToAvroTransformer = transformer;
    }

    @Override
    public void onStatus(Status status) {
        LOG.info("Received status text {} sending to kafka topic {}", status.getText(), kafkaConfigData.getTopicName());
        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
