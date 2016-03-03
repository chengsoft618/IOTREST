package cn.cjtblog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.NodeDAO;
import cn.cjtblog.dao.SensorDAO;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.service.SensorService;
import cn.cjtblog.util.BeanUtil;
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class SensorServiceImpl implements SensorService {
	private SensorDAO sensorDAO;
	private NodeDAO nodeDAO;

	@Override
	public Sensor addSensor(long nodeId,Sensor sensor) {
		Node node = nodeDAO.getById(nodeId);
		if (node != null) {
			sensor.setNode(node);
			sensorDAO.add(sensor);
			return sensor;
		} else {
			return null;
		}
	}

	@Override
	public Sensor addSensor(long nodeId, Map<String, Object> fieldMap) {
		Node node = nodeDAO.getById(nodeId);
		if (node != null) {
			Sensor sensor = BeanUtil.createEntity(Sensor.class, fieldMap);
			sensor.setNode(node);
			sensorDAO.add(sensor);
			return sensor;
		} else {
			return null;
		}
	}

	@Override
	public void updateSensor(long id, Map<String, Object> fieldMap) {
		Sensor sensor = sensorDAO.getById(id);
		if (sensor != null) {
			BeanUtil.updateEntity(sensor, fieldMap);
			sensorDAO.update(sensor);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Sensor getSensorById(long id) {
		// TODO Auto-generated method stub
		return sensorDAO.getById(id);
	}

	@Override
	public void deleteSensorById(long id) {
		sensorDAO.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Set<Sensor> getAllSensorsByNodeId(long nodeId) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		return node.getSensors();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Sensor> getAllSensors() {
		// TODO Auto-generated method stub
		return sensorDAO.getAll();
	}

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
