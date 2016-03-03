package cn.cjtblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.HumidityDAO;
import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.domain.Smoke;
import cn.cjtblog.service.HumidityService;
import cn.cjtblog.util.BeanUtil;
import cn.cjtblog.util.SensorType;

public class HumidityServiceImpl extends BaseSensorDataServiceImpl implements HumidityService {
	private HumidityDAO humidityDAO;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Humidity getHumidity(long nodeId, long sensorId,long id) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);

		Sensor sensor=sensorDAO.getById(sensorId);
	
		Humidity humidity=humidityDAO.getById(id);

		if(node!=null&&sensor!=null&&humidity!=null){
			if(sensor.getNode().getId()==node.getId()&&humidity.getSensor().getId()==sensor.getId()){
				return humidity;
			}
		}
		return null;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Humidity getNewestHumidity(long nodeId) {
		// TODO Auto-generated method stub
		return humidityDAO.getNewest(nodeId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Humidity getNewestHumidity(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return humidityDAO.getNewest(nodeId, sensorId);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Humidity> getHumiditiesByTimeRange(long nodeId,
			long sensorId, Date begin, Date end) {
			return humidityDAO.getByTimeRange(nodeId,sensorId, begin, end);

	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Humidity> getHumiditiesByTimeRange(long nodeId, Date begin,
			Date end) {
		// TODO Auto-generated method stub
	return humidityDAO.getByTimeRange(nodeId,begin, end);
	}
	@Override
	public List<Humidity> getAllHumidities(long nodeId) {
		// TODO Auto-generated method stub
		return humidityDAO.getAll(nodeId);
	}

	
	@Override
	public Humidity addHumidity(long nodeId, long sensorId, Map<String,Object> fieldMap) {
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.HUMIDITY.getType())&&sensor.getNode().getId()==node.getId()){
			fieldMap.put("sensor", sensor);
			Humidity humidity=BeanUtil.createEntity(Humidity.class, fieldMap);
			humidityDAO.add(humidity);
			return humidity;
		}
		return null;
	}
	
	
	@Override
	public Humidity addHumidity(long nodeId, long sensorId, Humidity humidity) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.HUMIDITY.getType())&&sensor.getNode().getId()==node.getId()){
			humidity.setSensor(sensor);
			humidity.setReceiveTime(new Date());
			humidityDAO.add(humidity);
			return humidity;
		}
		return null;
	}
	

	@Override
	public void deleteHumidityById(long id) {
		humidityDAO.deleteById(id);
		
	}
	@Override
	public List<Humidity> getAllHumidities(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return humidityDAO.getAll(nodeId,sensorId);
	}
	public HumidityDAO getHumidityDAO() {
		return humidityDAO;
	}
	public void setHumidityDAO(HumidityDAO humidityDAO) {
		this.humidityDAO = humidityDAO;
	}




}
