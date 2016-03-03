package cn.cjtblog.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL) 
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Sensor extends BaseEntity {
	private String type;
//	@JsonBackReference
	@ManyToOne
	private Node node;
/*	@OneToMany(mappedBy="sensor")
	private Set<SensorData> sensorDatas=new HashSet<>();*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}

	
}
