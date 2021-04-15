package nz.co.datacom.kafka.connect.upwork_connect.rss;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import nz.co.datacom.kafka.connect.upwork_connect.MySourceConnectorConfig;
import nz.co.datacom.kafka.connect.upwork_connect.rss.upwork.Rss;
 

public class RSSHandler {
//	public static String RSS_URL="https://www.upwork.com/ab/feed/topics/rss?securityToken=0561af26a8ae31e0583280eee4f9187a491b9ae692ba582360fa6f7eb3649a941e7ebeb36615900e52b5d04efe88883f89b0464a95d7543f14d5ea0b2592873b&userUid=1086524631491592192&orgUid=1086524631499980801&topic=4939097";
	  static final Logger log = LoggerFactory.getLogger(RSSHandler.class);
	 private static Long sleepTime=1L;
	 
    public static Rss getFeeds(String rss_url)
    {
    	 
    	 HttpResponse<String> resp= Unirest.get(rss_url) 
    		   // .header("accept", "application/json")
    		 //   .queryString("apiKey", "123")
    		    //.field("parameter", "value")
    		    //.field("firstname", "Gary")
    		   // .asJson();
    		 	.asString();
    		 
    	
    	switch(resp.getStatus()) {
    	case 200: 
    		 sleepSometime(sleepTime); 
    		return javax.xml.bind.JAXB.unmarshal(new StringReader(resp.getBody()), Rss.class);
    		 
    	default:
    		log.error(resp.getStatusText());
    		 sleepSometime(5*sleepTime);
    		return new Rss();
    	
    	}
    			
    		 
    }
    
   public  static void setSleepTime(int pollTime) {
	   if(pollTime>0)
    	sleepTime= pollTime*1000L;
	   else {
		   log.info(String.format("%s is not provided so setting default value to %s", MySourceConnectorConfig.RSS_POLL_SLEEP, sleepTime));
	   }
    }
    
   static private   void sleepSometime(Long sleepTime) {
  	  log.info(String.format("Sleeping for %s seconds",sleepTime/1000));
  	  try {
		Thread.sleep(sleepTime);
	} catch (InterruptedException e) {
		log.error(e.getMessage());
		e.printStackTrace();
		
	}
  	
    }
}
