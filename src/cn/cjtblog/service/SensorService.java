package cn.cjtblog.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.cjtblog.domain.Sensor;

public interface SensorService {
	public Sensor addSensor(long nodeId,Sensor sensor);
	public Sensor  addSensor(long nodeId, Map<String, Object> fieldMap);
    public void updateSensor(long id,Map<String,Object> fieldMap);
    public Sensor getSensorById(long id);
	public void deleteSensorById(long id);
	public Set<Sensor> getAllSensorsByNodeId(long nodeId);
	public List<Sensor> getAllSensors();
}
