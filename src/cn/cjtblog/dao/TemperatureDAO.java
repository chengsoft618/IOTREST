package cn.cjtblog.dao;

import java.util.Date;
import java.util.List;

import cn.cjtblog.domain.Smoke;
import cn.cjtblog.domain.Temperature;

public interface TemperatureDAO{
	public void add(Temperature temperature);
    public void update(Temperature temperature);
    public Temperature getById(long id);
	public void deleteById(long id);
	public void delete(Temperature temperature);
	public List<Temperature> getAll();
	public List<Temperature> getAll(long nodeId);
	public List<Temperature> getAll(long nodeId, long sensorId);
	
	
	public List<Temperature> getByTimeRange(long nodeId,Date begin, Date end);
	public List<Temperature> getByTimeRange(long nodeId,long sensorId,Date begin, Date end);
	public Temperature getNewest(long nodeId, long sensorId);
	public Temperature getNewest(long nodeId);
	public Temperature getNewest();
}
