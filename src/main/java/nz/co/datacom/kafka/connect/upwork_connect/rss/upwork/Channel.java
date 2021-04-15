package nz.co.datacom.kafka.connect.upwork_connect.rss.upwork;

import java.time.Instant;
import java.util.ArrayList;
 

import javax.xml.bind.annotation.XmlElement;

public class Channel {
	
	private String title;
	private String link;
	
	private String description;
	
	private String language;
	@XmlElement(name="pubDate" )
	
	private String pubDate;
	private String copyright;
	private String docs;
	private String generator;
	private String managingEditor;
	
	@XmlElement(name="image",type=Image.class)
	private Image image;
	
	@XmlElement(name="item",type=Item.class)
	private ArrayList<Item> items;
	
	
	
	 
	public Channel(String title, String link, String description, String language, String pubDate, String copyright,
			String docs, String generator, String managingEditor, Image image, ArrayList<Item> items) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.pubDate = pubDate;//Instant.parse(managingEditor);
		this.copyright = copyright;
		this.docs = docs;
		this.generator = generator;
		this.managingEditor = managingEditor;
		this.image = image;
		this.items = items;
	}
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
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
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPubDate() {
		return this.pubDate ;
		
	}
 
	public String getCopyright() {
		return this.copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getDocs() {
		return this.docs;
	}
	public void setDocs(String docs) {
		this.docs = docs;
	}
	public String getGenerator() {
		return this.generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public String getManagingEditor() {
		return this.managingEditor;
	}
	public void setManagingEditor(String managingEditor) {
		this.managingEditor = managingEditor;
	}
	/*
	 * public Image getImage() { return this.image; } public void setImage(Image
	 * image) { this.image = image; } public void setItems(ArrayList<Item> items) { this.items =
	 * items; }
	 */
	 public ArrayList<Item> getItems() { 
		 return
			 this.items; }
	
	
}
