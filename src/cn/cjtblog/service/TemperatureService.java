package cn.cjtblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cjtblog.domain.Temperature;

public interface TemperatureService {

	Temperature getTemperature(long nodeId, long sensorId, long id);

	Temperature addTemperature(long nodeId, long sensorId, Map<String, Object> fieldMap);
	Temperature addTemperature(long nodeId, long sensorId,
			Temperature temperature);
	Temperature getNewestTemperature(long nodeId, long sensorId);

	List<Temperature> getAllTemperatures(long nodeId, long sensorId);

	void deleteTemperatureById(long id);

	List<Temperature> getTemperatureByTimeRange(long nodeId, long sensorId,
			Date begin, Date end);

	List<Temperature> getAllTemperatures(long nodeId);

	List<Temperature> getTemperatureByTimeRange(long nodeId, Date begin,
			Date end);



	Temperature getNewestTemperature(long nodeId);
}
