package cn.cjtblog.domain;

import javax.persistence.Entity;
@Entity
public class Temperature extends SensorData{

	public final static String UNIT = "℃";



	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


}
