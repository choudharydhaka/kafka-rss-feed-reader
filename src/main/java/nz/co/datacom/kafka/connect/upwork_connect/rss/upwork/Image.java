package nz.co.datacom.kafka.connect.upwork_connect.rss.upwork;

public class Image {
	private String url;
	private String title;
	
	private String link;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(String url, String title, String link) {
		super();
		this.url = url;
		this.title = title;
		this.link = link;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	
}
