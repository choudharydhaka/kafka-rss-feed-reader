package nz.co.datacom.kafka.connect.upwork_connect;  
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.ITEM_DESCRIPTION_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.ITEM_GUID_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.ITEM_LINK_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.ITEM_PUBDATE_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.ITEM_TITLE_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.KEY_SCHEMA;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.PUBLISHED_AT_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.RSS_FIELD;
import static nz.co.datacom.kafka.connect.upwork_connect.UpworkSchemas.VALUE_SCHEMA;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.co.datacom.kafka.connect.upwork_connect.rss.RSSHandler;
import nz.co.datacom.kafka.connect.upwork_connect.rss.upwork.Item;
import nz.co.datacom.kafka.connect.upwork_connect.rss.upwork.Rss;
import nz.co.datacom.kafka.connect.upwork_connect.utils.DateUtils;

public class MySourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
  public MySourceConnectorConfig config;
  
  
  protected Instant lastUpdatedAt;
  private int filterFeedSince;
  
  @Override
  public String version() {
    return VersionUtil.getVersion();
  }


  @Override
  public void start(Map<String, String> map) {
      //Do things here that are required to start your task. This could be open a connection to a database, etc.
      config = new MySourceConnectorConfig(map);
      initializeLastVariables();
     // gitHubHttpAPIClient = new GitHubAPIHttpClient(config);
  }

  private void initializeLastVariables() {
	   Map<String, Object> lastSourceOffset = null;
       lastSourceOffset = context.offsetStorageReader().offset(sourcePartition());
       if( lastSourceOffset == null) 
           // we haven't fetched anything yet, so we initialize to 7 days ago
    	   lastUpdatedAt=Instant.now();
    	   else {
	    	   String updatedAt = (String) lastSourceOffset.get(PUBLISHED_AT_FIELD);
	          
	           if(updatedAt == null && (updatedAt instanceof String)) 
	        	   lastUpdatedAt = Instant.ofEpochMilli(Long.valueOf(updatedAt));
	           else lastUpdatedAt=Instant.now();
    	   }
        log.info("Poll Sleept time is " + config.getRssPollSleep());
     RSSHandler.setSleepTime(config.getRssPollSleep());
}


private Map<String, String> sourcePartition() {
	   Map<String, String> map = new HashMap<>();
	   map.put(RSS_FIELD, config.getRssFeedUrl());
       return map;
 
}
private Map<String, String> sourceOffset(String publishedDate) {
    Map<String, String> map = new HashMap<>();
    map.put(PUBLISHED_AT_FIELD, String.valueOf(lastUpdatedAt.toEpochMilli()) );
     
    return map;
}

@Override
  public List<SourceRecord> poll() throws InterruptedException {
    final ArrayList<SourceRecord> records = new ArrayList<>();
    Rss rss = RSSHandler.getFeeds(config.getRssFeedUrl()) ;
    // we'll count how many results we get with i
    if(!DateUtils.isLatestFeed  (lastUpdatedAt,rss.getChannel().getPubDate() ))
    {
    	log.info("Existing Published Feed, so skipping it!");
    }
    
    else {
    
    	log.info(String.format("Fetched %s record(s)", rss.getChannel().getItems().size()));
    
  
    	for (Item item : rss.getChannel().getItems()) {
    		  if(DateUtils.checkDate(rss.getChannel().getPubDate())) {
    		    	// Check if the business requirement is met
			        SourceRecord sourceRecord = generateSourceRecord(item,rss);
			        records.add(sourceRecord);
			       
			       
    	}
   
    }
    	log.info(String.format("Kakfa - Adding %s Record(s)",records.size()) );
    	 lastUpdatedAt = DateUtils.getPubDate(rss.getChannel().getPubDate());
    }
    
    return records;
    
  }

  private SourceRecord generateSourceRecord(Item item,Rss rss) {
	  return new SourceRecord(
              sourcePartition(),
              sourceOffset(rss.getChannel().getPubDate()),
              config.getTopic(),
              null, // partition will be inferred by the framework
              KEY_SCHEMA,
              buildRecordKey(item),
              VALUE_SCHEMA,
              buildRecordValue(item)
//        /*   new Object( DateUtils.getPubDateEpoch(
		
		
			  );
}

  private Struct buildRecordKey(Item item){
      // Key Schema
      Struct key = new Struct(KEY_SCHEMA)
              .put(ITEM_GUID_FIELD, item.getGuid());
              //.put(RSS_FEED_URL, config.getRssFeedUrl)
            

      return key;
  }

  public Struct buildRecordValue(Item item){

      // Issue top level fields
      Struct valueStruct = new Struct(VALUE_SCHEMA)
    		  
    		 
              .put(ITEM_TITLE_FIELD, item.getTitle())
              .put(ITEM_LINK_FIELD, item.getLink())
              .put(ITEM_DESCRIPTION_FIELD, item.getDescription())
              .put(ITEM_PUBDATE_FIELD, item.getPubDate())
              .put(ITEM_GUID_FIELD,item.getGuid());
               

  

      return valueStruct;
  }

@Override
  public void stop() {
    //TODO: Do whatever is required to stop your task.
  }


public void setLastUpdatedAt(Instant lastUpdatedAt) {
	this.lastUpdatedAt = lastUpdatedAt;
}


}