package cn.cjtblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.SmokeDAO;
import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.domain.Smoke;
import cn.cjtblog.service.SmokeService;
import cn.cjtblog.util.BeanUtil;
import cn.cjtblog.util.SensorType;

public class SmokeServiceImpl extends BaseSensorDataServiceImpl implements
		SmokeService {
	private SmokeDAO smokeDAO;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Smoke getSmoke(long nodeId, long sensorId, long id) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);

		Sensor sensor=sensorDAO.getById(sensorId);
	
		Smoke smoke=smokeDAO.getById(id);

		if(node!=null&&sensor!=null&&smoke!=null){
			if(sensor.getNode().getId()==node.getId()&&smoke.getSensor().getId()==sensor.getId()){
				return smoke;
			}
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Smoke> getAllSmokes(long nodeId) {
		// TODO Auto-generated method stub
		return smokeDAO.getAll(nodeId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Smoke> getAllSmokes(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return smokeDAO.getAll(nodeId, sensorId);
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Smoke getNewestSmoke(long nodeId) {
		// TODO Auto-generated method stub
		return smokeDAO.getNewest(nodeId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Smoke getNewestSmoke(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return smokeDAO.getNewest(nodeId, sensorId);
	}

	@Override
	public List<Smoke> getSmokesByTimeRange(long nodeId, Date begin, Date end) {
		// TODO Auto-generated method stub
		return smokeDAO.getByTimeRange(nodeId, begin, end);
	}

	@Override
	public List<Smoke> getSmokesByTimeRange(long nodeId, long sensorId,
			Date begin, Date end) {
		// TODO Auto-generated method stub
		return smokeDAO.getByTimeRange(nodeId,sensorId, begin, end);
	}
	
	@Override
	public Smoke addSmoke(long nodeId, long sensorId,
			Map<String, Object> fieldMap) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.SMOKE.getType())&&sensor.getNode().getId()==node.getId()){
			fieldMap.put("sensor", sensor);
			Smoke smoke=BeanUtil.createEntity(Smoke.class, fieldMap);
			smokeDAO.add(smoke);
			return smoke;
		}
		return null;
	}

	@Override
	public Smoke addSmoke(long nodeId, long sensorId, Smoke smoke) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.SMOKE.getType())&&sensor.getNode().getId()==node.getId()){
			smoke.setSensor(sensor);
			smoke.setReceiveTime(new Date());
			smokeDAO.add(smoke);
			return smoke;
		}
		return null;
	}


	@Override
	public void deleteSmokeById(long id) {
		// TODO Auto-generated method stub
		smokeDAO.deleteById(id);
	}

	public SmokeDAO getSmokeDAO() {
		return smokeDAO;
	}

	public void setSmokeDAO(SmokeDAO smokeDAO) {
		this.smokeDAO = smokeDAO;
	}







}
