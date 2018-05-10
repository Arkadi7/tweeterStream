package model;

public class Tweet {

  private String text;
  private String created_at;


  public Tweet(String text, String created_at) {

    this.text = text;
    this.created_at = created_at;

  }

  public String getCreated_at() {

    return created_at;
  }

  public void setCreated_at(String created_at) {

    this.created_at = created_at;
  }

  public String getText() {

    return text;
  }

  public void setText(String text) {

    this.text = text;
  }
}
