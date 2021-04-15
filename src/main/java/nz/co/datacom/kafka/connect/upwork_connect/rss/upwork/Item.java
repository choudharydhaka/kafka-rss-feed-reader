package nz.co.datacom.kafka.connect.upwork_connect.rss.upwork;

import java.time.Instant;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	
	private String title;
	private String link;
	
	private String description;
	@XmlElement(name="language" )
	private String language;
	private String pubDate;
	private String guid;
	
	   @XmlElement(namespace="http://purl.org/rss/1.0/modules/content/")
 
	private String content;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String title, String link, String description, String language, String pubDate, String guid,
			String content) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		 
			this.pubDate = pubDate;
		 
		this.guid = guid;
		this.content = content;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return this.link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return this.language;
	}
//	public void setLanguage(String language) {
//		this.language = language;
//	}
	public String getPubDate() {
		return this.pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate =pubDate;
	}
	public String getGuid() {
		return this.guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getContent() {
		return this.content;
	}
//	public void setContent(String content) {
//		this.content = content;
//	}
	
}
