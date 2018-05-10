package model;

import twitter4j.JSONObject;

import java.util.Map;

public class myDTO {

  private String uhh_id;
  private Map tweet;

  public myDTO(String id, Map tweet) {

    this.uhh_id = id;
    this.tweet = tweet;
  }

  public String getUhh_id() {

    return uhh_id;
  }

  public void setUhh_id(String uhh_id) {

    this.uhh_id = uhh_id;
  }

  public Map getTweet() {

    return tweet;
  }

  public void setTweet(Map tweet) {

    this.tweet = tweet;
  }
}
