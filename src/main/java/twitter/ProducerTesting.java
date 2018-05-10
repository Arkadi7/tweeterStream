package twitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import kafka.KafkaProducer;
import model.myDTO;
import twitter4j.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProducerTesting implements UserStreamListener {

  private final CountDownLatch latch = new CountDownLatch(3);

  private final KafkaProducer kafkaProducer;

  public ProducerTesting(KafkaProducer kafkaProducer) {

    this.kafkaProducer = kafkaProducer;
  }

  public void onStatus(Status status) {

    System.out.println("onStatus @" + status.getUser().getScreenName() + " - " + status.getText());
    //Tweet tweet = new Tweet(status.getText(), status.getCreatedAt().toString());
    Map<String, Object> data = null;
    try {
      String string = new JSONObject(TwitterObjectFactory.getRawJSON(status)).toString();
      final ObjectMapper mapper = new ObjectMapper();
      final MapType type = mapper.getTypeFactory().constructMapType(
        Map.class, String.class, Object.class);

      data = mapper.readValue(string, type);
    }
    catch (IOException | JSONException e) {
      e.printStackTrace();
    }
    String userId = "" + status.getUser().getId() + "|" + status.getCreatedAt().getTime();
    myDTO my = new myDTO(userId, data);
    this.kafkaProducer.tweet(my);
    try {
      latch.await(0, TimeUnit.SECONDS);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

  }

  public void onDeletionNotice(long directMessageId, long userId) {

  }

  public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

  }

  public void onScrubGeo(long userId, long upToStatusId) {

  }

  public void onStallWarning(StallWarning warning) {

    System.out.println("Got stall warning:" + warning);
  }

  public void onFriendList(long[] friendIds) {

  }

  public void onFavorite(User source, User target, Status favoritedStatus) {

  }

  public void onUnfavorite(User source, User target, Status unfavoritedStatus) {

  }

  public void onFollow(User source, User followedUser) {

  }

  public void onUnfollow(User source, User followedUser) {

  }

  public void onDirectMessage(DirectMessage directMessage) {

  }

  public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {

  }

  public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {

  }

  public void onUserListSubscription(User subscriber, User listOwner, UserList list) {

  }

  public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {

  }

  public void onUserListCreation(User listOwner, UserList list) {

  }

  public void onUserListUpdate(User listOwner, UserList list) {

  }

  public void onUserListDeletion(User listOwner, UserList list) {

  }

  public void onUserProfileUpdate(User updatedUser) {

  }

  public void onUserDeletion(long deletedUser) {

    System.out.println("onUserDeletion User:@" + deletedUser);
  }

  public void onUserSuspension(long suspendedUser) {

    System.out.println("onUserSuspension User:@" + suspendedUser);
  }

  public void onBlock(User source, User blockedUser) {

  }

  public void onUnblock(User source, User unblockedUser) {

  }

  public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {

  }

  public void onFavoritedRetweet(User source, User target, Status favoritedRetweet) {

  }

  public void onQuotedTweet(User source, User target, Status quotingTweet) {

  }

  public void onException(Exception ex) {

  }

}
