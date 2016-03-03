package cn.cjtblog.domain;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class SensorData extends BaseEntity {
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss:SSS")
	private Date receiveTime;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss:SSS")
	private Date sendTime;
	@ManyToOne
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
}
