package nz.co.datacom.kafka.connect.upwork_connect;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Timestamp;

public class UpworkSchemas {
 
	      
	   
	 	public static final String PUBLISHED_AT_FIELD = "pubDate";
	 	  public static final String RSS_FEED_URL = "rss.upwork.feed.url";	 
			 
	    public static final String RSS_FIELD = "rss";

	    // item fields
	    public static final String ITEM_TITLE_FIELD = "title";
	    public static final String ITEM_LINK_FIELD = "link";
	    public static final String ITEM_DESCRIPTION_FIELD = "description";
	    public static final String ITEM_PUBDATE_FIELD = "pubDate";
	    public static final String ITEM_GUID_FIELD = "guid"; 

	    // image fields
	    public static final String IMAGE_TITLE_FIELD = "title";
	    public static final String IMAGE_LINK_FIELD = "link";
	    public static final String IMAGE_URL_FIELD = "url";
	    
	    // channel fields
	    public static final String CHANNEL_FIELD = "pull_request";
	    public static final String CHANNEL_URL_FIELD = "url";
	    public static final String CHANNEL_HTML_URL_FIELD = "html_url";
	    public static final String  CHANNEL_DESCRIPTION_FIELD = "description";
	    public static final String CHANNEL_LANGUAGE_FIELD = "language";
	    public static final String CHANNEL_PUBDATE_FIELD = "pubDate";
	    public static final String CHANNEL_COPYRIGHT_FIELD = "copyright";
	    public static final String CHANNEL_DOC_FIELD = "docs";
	    public static final String CHANNEL_GENERATOR_FIELD = "generator";
	    public static final String CHANNEL_MANAGINGEDITOR_FIELD = "managingEditor";
	    
	    // Schema names
	    public static final String SCHEMA_KEY = "com.simplesteph.kafka.connect.upwork.FeedKey";
	    public static final String SCHEMA_VALUE_CHANNEL = "com.simplesteph.kafka.connect.upwork.ChannelValue";
	    public static final String SCHEMA_VALUE_IMAGE = "com.simplesteph.kafka.connect.upwork.ImageValue";
	    public static final String SCHEMA_VALUE_ITEM = "com.simplesteph.kafka.connect.upwork.ITEMValue";

	    // Key Schema
	    public static final Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY)
	            .version(1)
	            .field(ITEM_GUID_FIELD, Schema.STRING_SCHEMA)
	           // .field(RSS_FEED_URL, Schema.STRING_SCHEMA)
	            .build();

	    // Value Schema
 

	    public static final Schema VALUE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_ITEM)
	            .version(2)
	            .field(ITEM_TITLE_FIELD, Schema.STRING_SCHEMA)
	            .field(ITEM_LINK_FIELD, Schema.STRING_SCHEMA)
	            .field(ITEM_DESCRIPTION_FIELD,Schema.STRING_SCHEMA)
	            .field(ITEM_PUBDATE_FIELD, Schema.STRING_SCHEMA)
	            .field(ITEM_GUID_FIELD, Schema.STRING_SCHEMA)
	          
	            .build();
	}
 
