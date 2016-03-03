package cn.cjtblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Smoke;

public interface SmokeService {
	Smoke getSmoke(long nodeId, long sensorId, long id);

	Smoke addSmoke(long nodeId, long sensorId, Map<String, Object> fieldMap);
	Smoke addSmoke(long nodeId, long sensorId, Smoke smoke);
	Smoke getNewestSmoke(long nodeId);
	Smoke getNewestSmoke(long nodeId, long sensorId);


	List<Smoke> getAllSmokes(long nodeId);
	List<Smoke> getAllSmokes(long nodeId,long sensorId);
	
	List<Smoke> getSmokesByTimeRange(long nodeId, Date begin, Date end);
	List<Smoke> getSmokesByTimeRange(long nodeId,long sensorId, Date begin, Date end);
	
	void deleteSmokeById(long id);
}
