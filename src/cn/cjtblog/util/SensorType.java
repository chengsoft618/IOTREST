package cn.cjtblog.util;

public enum SensorType {
	TEMPERATURE("temperature"),
	SMOKE("smoke"),
	HUMIDITY("humidity"),
	IMAGE("image");
	private String type;
	SensorType(String type){
		this.type=type;
	}
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
