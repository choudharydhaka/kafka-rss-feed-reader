package nz.co.datacom.kafka.connect.upwork_connect.rss.upwork;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rss")
public class Rss {

	@XmlElement(name = "channel", type = Channel.class)
	private Channel channel;

	public Rss(Channel channel) {
		super();
		this.channel = channel;

	}

	public Rss() {
		super();
		 this.channel=new Channel();
	}

	 public Channel getChannel() { return this.channel; }
	 
 

}
