package kafka;

import model.myDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
  private final KafkaTemplate<String, myDTO> kafkaTemplate;

  private String kafkaTopic;

  public KafkaProducer(KafkaTemplate<String, myDTO> kafkaTemplate, String kafkaTopic) {

    this.kafkaTemplate = kafkaTemplate;
    this.kafkaTopic = kafkaTopic;
  }

  public void tweet(myDTO tweet) {


    ListenableFuture<SendResult<String, myDTO>> result = kafkaTemplate
      .send(kafkaTopic, tweet);

    log.info(String.valueOf(result));
  }
}
