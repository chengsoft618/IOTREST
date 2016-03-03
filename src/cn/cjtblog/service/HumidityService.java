package cn.cjtblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Temperature;

public interface HumidityService {


	Humidity addHumidity(long nodeId, long sensorId,
			Map<String, Object> fieldMap);
	Humidity addHumidity(long nodeId, long sensorId, Humidity humidity);
	Humidity getNewestHumidity(long nodeId);

	Humidity getNewestHumidity(long nodeId, long sensorId);

	List<Humidity> getAllHumidities(long nodeId);

	List<Humidity> getAllHumidities(long nodeId, long sensorId);

	void deleteHumidityById(long id);

	List<Humidity> getHumiditiesByTimeRange(long nodeId, Date begin, Date end);

	List<Humidity> getHumiditiesByTimeRange(long nodeId, long sensorId,
			Date begin, Date end);

	Humidity getHumidity(long nodeId, long sensorId, long id);



}
