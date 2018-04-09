package kafka;

import model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
  private final KafkaTemplate<String, Tweet> kafkaTemplate;

  private String kafkaTopic;

  public KafkaProducer(KafkaTemplate<String, Tweet> kafkaTemplate, String kafkaTopic) {

    this.kafkaTemplate = kafkaTemplate;
    this.kafkaTopic = kafkaTopic;
  }

  public void tweet(Tweet tweet) {

    log.info("sending data='{}'", tweet);
    kafkaTemplate.send(kafkaTopic, tweet);
  }
}
