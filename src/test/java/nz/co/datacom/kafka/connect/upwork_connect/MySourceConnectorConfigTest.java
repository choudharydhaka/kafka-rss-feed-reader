package nz.co.datacom.kafka.connect.upwork_connect;

 
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.junit.Before;
import org.junit.Test;

 
import static nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig.*;
import static org.junit.Assert.assertTrue;

public class MySourceConnectorConfigTest {
  
  
  private ConfigDef configDef = MySourceConnectorConfig.conf();
  private Map<String, String> config;

  @Before
  public void setUpInitialConfig() 
  {
	  
	  config = new HashMap<>();
      config.put(RSS_FEED_URL, "https://www.upwork.com/ab/feed/topics/rss?securityToken=0561af26a8ae31e0583280eee4f9187a491b9ae692ba582360fa6f7eb3649a941e7ebeb36615900e52b5d04efe88883f89b0464a95d7543f14d5ea0b2592873b&userUid=1086524631491592192&orgUid=1086524631499980801&topic=4939097");
      config.put(RSS_FEED_SINCE, "2");
      config.put(TOPIC_CONFIG, "rss-feed");  
  } 
  
  @Test
  public void doc() {
      System.out.println(MySourceConnectorConfig.conf().toRst());
  }

//  @Test
//  public void initialConfigIsValid() {
//      assertTrue(configDef.validate(config)
//              .stream()
//              .allMatch(configValue -> configValue.errorMessages().size() == 0));
//  }
//
//  @Test
//  public void canReadConfigCorrectly() {
//      MySourceConnectorConfig config = new MySourceConnectorConfig(this.config);
//      config.getRssFeedUrl();
//
//  }

}