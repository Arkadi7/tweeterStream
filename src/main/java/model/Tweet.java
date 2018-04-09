package model;

public class Tweet {
String id;
String Text;

  public Tweet(String id, String text) {

    this.id = id;
    Text = text;
  }

  public String getId() {

    return id;
  }

  public void setId(String id) {

    this.id = id;
  }

  public String getText() {

    return Text;
  }

  public void setText(String text) {

    Text = text;
  }
}
