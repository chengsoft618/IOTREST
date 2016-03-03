package cn.cjtblog.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.NodeDAO;
import cn.cjtblog.dao.SensorDAO;
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public abstract class BaseSensorDataServiceImpl {
	protected SensorDAO sensorDAO;
	protected NodeDAO nodeDAO;
	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}
	public void setSensorDAO(SensorDAO sensorDAO) {
		this.sensorDAO = sensorDAO;
	}
	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}
	public void setNodeDAO(NodeDAO nodeDAO) {
		this.nodeDAO = nodeDAO;
	}
}
