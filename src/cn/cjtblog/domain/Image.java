package cn.cjtblog.domain;

import javax.persistence.Entity;
@Entity
public class Image extends SensorData{

	public static final String SUFFIX = ".jpg";
	public static final String BASICIMAGEDIR = System.getProperty("IOTREST.root")+"IOTImage/";
	public static final String BASICIMAGEURL = "IOTImage/";
	private String URL;
	public String getURL() {
		return URL;
	}
	public void setURL(String URL) {
		this.URL = URL;
	}

}
