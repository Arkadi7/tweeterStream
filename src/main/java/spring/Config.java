package spring;

import kafka.KafkaProducer;
import model.Tweet;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import twitter.TwitterKafkaServiceTesting;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:/application.properties")
public class Config {

  @Value("${twitter.ConsumerKey}")
  private String key;
  @Value("${twitter.ConsumerSecret}")
  private String keySecret;
  @Value("${twitter.AccessToken}")
  private String token;
  @Value("${twitter.AccessTokenSecret}")
  private String tokenSecret;
  @Value("${kafka.out.topic}")
  private String kafkaOutTopic;

  @Bean
  public KafkaTemplate<String, Tweet> kafkaTemplate(
    @Value("${kafka.bootstrap-servers}") String bootstrap) {

    Map<String, Object> producerConfigs = new HashMap<>();
    producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
    producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                        StringSerializer.class.getName());
    producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    ProducerFactory<String, Tweet> producerFactory = new DefaultKafkaProducerFactory<>(
      producerConfigs);
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  public KafkaProducer kafkaProducer(KafkaTemplate<String, Tweet> kafkaTemplate,
                                     @Value("${kafka.in.save.topic}")
                                       String kafkaOutTopic) {

    return new KafkaProducer(kafkaTemplate, kafkaOutTopic);
  }

  @Bean
  public TwitterKafkaServiceTesting twitterKafkaServiceTesting(KafkaProducer kafkaProducer) {

    ConfigurationBuilder configuration = new ConfigurationBuilder();
    configuration.setDebugEnabled(true)
      .setOAuthConsumerKey(key)
      .setOAuthConsumerSecret(keySecret)
      .setOAuthAccessToken(token)
      .setOAuthAccessTokenSecret(tokenSecret);

    return new TwitterKafkaServiceTesting(configuration, kafkaProducer);
  }
}
