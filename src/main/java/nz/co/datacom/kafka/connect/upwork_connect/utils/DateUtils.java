package nz.co.datacom.kafka.connect.upwork_connect.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.co.datacom.kafka.connect.upwork_connect.MySourceTask;

public class DateUtils {
	 static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
	   private static final String DATE_TIME_FORMATE = "EEE, dd MMM yyyy HH:mm:ss Z"; 
	static DateFormat dateFormat= new SimpleDateFormat(
			DATE_TIME_FORMATE, Locale.US); ;
	  
    public static Instant MaxInstant (Instant i1, Instant i2){
        return i1.compareTo(i2) > 0 ? i1 : i2;
    }
    
 
	private static final int LAST_FEED_DAYS = 2;

	public static long getPubDateEpoch(String pubDate) {
		 
		
		try {
			return dateFormat.parse(pubDate).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Calendar.getInstance().getTimeInMillis();
		}
		
	}
	public static boolean checkDate(String time) {

		
	 //GregorianCalendar(year + 1900, month, date).
	 Calendar today=Calendar.getInstance();
	 GregorianCalendar cal=new GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH) - LAST_FEED_DAYS );
	 
	try {
		//compare time variable value with LAST_FEED_DAYS 
		
		log.info(String.format("Published Data %s, Barred Date to Compare %s, Result %s",dateFormat.parse(time).getTime(),cal.getTimeInMillis(), dateFormat.parse(time).getTime() > cal.getTimeInMillis()));
		return dateFormat.parse(time).getTime() > cal.getTimeInMillis();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	 
	return false;
	 
	}
	
	public static boolean isLatestFeed(Instant lastUpdate, String currentTime) {

		
		 
		try {
			log.info(String.format("Last Published Data %s, Current Publishded data to Compare %s, Result %s", lastUpdate.toEpochMilli()  ,dateFormat.parse(currentTime).getTime(), lastUpdate.getEpochSecond()  < dateFormat.parse(currentTime).getTime()));
			return  lastUpdate.toEpochMilli()  < dateFormat.parse(currentTime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		 
		return false;
		 
		}
	
	public static Instant getPubDate(String pubDate) {
		// TODO Auto-generated method stub
		return Instant.ofEpochMilli(getPubDateEpoch(pubDate) ) ;
	}
}
