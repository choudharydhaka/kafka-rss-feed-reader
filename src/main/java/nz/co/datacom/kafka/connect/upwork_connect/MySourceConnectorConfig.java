package nz.co.datacom.kafka.connect.upwork_connect;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;

import java.util.Map;


public class MySourceConnectorConfig extends AbstractConfig {

	
  public static final String MY_SETTING_CONFIG = "my.setting";
  private static final String MY_SETTING_DOC = "This is a setting important to my connector.";

  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = "Topic to write to";

  public static final String RSS_FEED_URL = "rss.feed.url";
  private static final String RSS_FEED_URL_DOC = "RSS Feed URL";
 

  public static final String RSS_FEED_SINCE = "rss.since";
  private static final String RSS_FEED_SINCE_DOC = "Provide the count of the days from current date to process the feeds after, for example 2 ";

  public static final String RSS_POLL_SLEEP = "rss.sleep";
  private static final String RSS_POLL_SLEEP_DOC = "Provide sleep inteval to poll the rss feed in secs"; 
  
  public MySourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public MySourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(MY_SETTING_CONFIG, Type.STRING, Importance.LOW, MY_SETTING_DOC) 
    .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC) 
    .define(RSS_FEED_URL, Type.STRING, Importance.HIGH, RSS_FEED_URL_DOC)
    .define(RSS_FEED_SINCE, Type.INT, Importance.HIGH, RSS_FEED_SINCE_DOC)
    .define(RSS_POLL_SLEEP, Type.INT, Importance.HIGH, RSS_POLL_SLEEP_DOC);  
  }

  public String getMy(){
    return this.getString(MY_SETTING_CONFIG);
  }
  

  public String getTopic() {
      return this.getString(TOPIC_CONFIG);
  }
  public Integer getSince() {
      return this.getInt(RSS_FEED_SINCE);
  }
 

  public String getRssFeedUrl() {
      return this.getString(RSS_FEED_URL);
  }
  public Integer getRssPollSleep() {
      return this.getInt(RSS_POLL_SLEEP);
  }
}
