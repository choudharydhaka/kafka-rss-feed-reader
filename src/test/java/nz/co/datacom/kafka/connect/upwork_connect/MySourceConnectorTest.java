package nz.co.datacom.kafka.connect.upwork_connect;

import static nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MySourceConnectorTest {

	  private Map<String, String> initialConfig() {
	    Map<String, String> baseProps = new HashMap<>();
	    baseProps.put(RSS_FEED_URL, "foo");
	    baseProps.put(RSS_FEED_SINCE, "2");
	    
	    baseProps.put(TOPIC_CONFIG, "github-issues");
	    return (baseProps);
	  }

	  @Test
	  public void taskConfigsShouldReturnOneTaskConfig() {
		  Map<String, String> baseProps = new HashMap<>();
		    baseProps.put(RSS_FEED_URL, "foo");
		    baseProps.put(RSS_FEED_SINCE, "2");
		    baseProps.put(MY_SETTING_CONFIG, "test");
		    baseProps.put(TOPIC_CONFIG, "github-issues");
		  MySourceConnector gitHubSourceConnector = new MySourceConnector();
	      gitHubSourceConnector.start(baseProps);
	      
	      
	      assertEquals(gitHubSourceConnector.taskConfigs(1).size(),1);
	      assertEquals(gitHubSourceConnector.taskConfigs(10).size(),1);
	  }
}
