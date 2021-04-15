package nz.co.datacom.kafka.connect.upwork_connect;

import static nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig.RSS_FEED_SINCE;
import static nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig.RSS_FEED_URL;
import static org.junit.Assert.assertNotNull;
import static nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.connect.source.SourceRecord;
import org.junit.Test;

public class MySourceTaskTest {
	

    private MySourceTask mySourceTask = new MySourceTask();
    

    private Map<String, String> initialConfig() {
        Map<String, String> baseProps = new HashMap<>();
	    baseProps.put(RSS_FEED_URL, "https://www.upwork.com/ab/feed/topics/rss?securityToken=0561af26a8ae31e0583280eee4f9187a491b9ae692ba582360fa6f7eb3649a941e7ebeb36615900e52b5d04efe88883f89b0464a95d7543f14d5ea0b2592873b&userUid=1086524631491592192&orgUid=1086524631499980801&topic=4939097");
	    baseProps.put(RSS_FEED_SINCE, "2");
	    baseProps.put(MY_SETTING_CONFIG, "test");
	    
	    baseProps.put(TOPIC_CONFIG, "rss-feeds");
        return baseProps;
    }


    
    
  @Test
  public void test() throws InterruptedException {
		/*
		 * Map<String, String> parsedConfig =new HashMap();
		 * parsedConfig.put("covid19.api.url.summary",
		 * "https://www.upwork.com/ab/feed/topics/rss?securityToken=0561af26a8ae31e0583280eee4f9187a491b9ae692ba582360fa6f7eb3649a941e7ebeb36615900e52b5d04efe88883f89b0464a95d7543f14d5ea0b2592873b&userUid=1086524631491592192&orgUid=1086524631499980801&topic=4939097"
		 * );
		 */
		/*
		 * MySourceTask MySourceTask=new MySourceTask();
		 * MySourceTask.start(parsedConfig); MySourceTask.poll();
		 */
				mySourceTask.config = new MySourceConnectorConfig(initialConfig());
				mySourceTask.setLastUpdatedAt(Instant.now());
				 
				List<SourceRecord> list= mySourceTask.poll();
				
				 assertNotNull(list);
	 
//	            assertNotNull(issue);
//	            assertNotNull(issue.getNumber());
//	            assertEquals(2072, issue.getNumber().intValue());
	     
  }
}