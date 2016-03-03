package cn.cjtblog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Node extends BaseEntity {
    private double longitude;
    private double latitude;
    private String name;
    private String description;
//	@JsonManagedReference
    @OneToMany(mappedBy="node",fetch=FetchType.EAGER)
    private Set<Sensor> sensors=new HashSet<>();
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(Set<Sensor> sensors) {
		this.sensors = sensors;
	}
}
