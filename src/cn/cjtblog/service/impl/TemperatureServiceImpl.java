package cn.cjtblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.controller.rest.TemperatureController;
import cn.cjtblog.dao.TemperatureDAO;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.TemperatureService;
import cn.cjtblog.util.BeanUtil;
import cn.cjtblog.util.SensorType;

public class TemperatureServiceImpl extends BaseSensorDataServiceImpl implements
		TemperatureService {
	private static Logger logger=LoggerFactory.getLogger(TemperatureServiceImpl.class);
	private TemperatureDAO temperatureDAO;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Temperature getTemperature(long nodeId, long sensorId, long id) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);

		Sensor sensor=sensorDAO.getById(sensorId);
	
		Temperature temperature=temperatureDAO.getById(id);

		if(node!=null&&sensor!=null&&temperature!=null){
			if(sensor.getNode().getId()==node.getId()&&temperature.getSensor().getId()==sensor.getId()){
				return temperature;
			}
		}
		return null;
	}

	@Override
	public Temperature addTemperature(long nodeId, long sensorId,
			Map<String, Object> fieldMap) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.TEMPERATURE.getType())&&sensor.getNode().getId()==node.getId()){
			fieldMap.put("sensor", sensor);
			fieldMap.put("receiveTime", new Date());
			Temperature temperature=BeanUtil.createEntity(Temperature.class, fieldMap);
			temperatureDAO.add(temperature);
			return temperature;
		}
		return null;
	}

	@Override
	public Temperature addTemperature(long nodeId, long sensorId,
			Temperature temperature) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		logger.info(sensor.toString()+":"+sensor.getType());
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.TEMPERATURE.getType())&&sensor.getNode().getId()==node.getId()){
			temperature.setSensor(sensor);
			temperature.setReceiveTime(new Date());
			temperatureDAO.add(temperature);
			return temperature;
		}
		return null;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Temperature getNewestTemperature(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		Temperature temperature=temperatureDAO.getNewest();
		if(node!=null&&sensor!=null&&temperature!=null){
			if(sensor.getNode().getId()==node.getId()&&temperature.getSensor().getId()==sensor.getId()){
				return temperature;
			}
		}
		return null;
	}
	
	@Override
	public Temperature getNewestTemperature(long nodeId) {
		Temperature temperature=temperatureDAO.getNewest(nodeId);
		return temperature;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Temperature> getAllTemperatures(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return temperatureDAO.getAll(nodeId,sensorId);
	}

	@Override
	public void deleteTemperatureById(long id) {
		temperatureDAO.deleteById(id);

	}

	public TemperatureDAO getTemperatureDAO() {
		return temperatureDAO;
	}

	public void setTemperatureDAO(TemperatureDAO temperatureDAO) {
		this.temperatureDAO = temperatureDAO;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Temperature> getTemperatureByTimeRange(long nodeId,
			long sensorId, Date begin, Date end) {
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getNode().getId()==node.getId()){
			return temperatureDAO.getByTimeRange(sensorId, begin, end);
		}
		return null;
	}

	@Override
	public List<Temperature> getAllTemperatures(long nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Temperature> getTemperatureByTimeRange(long nodeId, Date begin,
			Date end) {
		// TODO Auto-generated method stub
		return null;
	}




}
