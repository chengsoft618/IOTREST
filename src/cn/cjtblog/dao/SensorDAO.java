package cn.cjtblog.dao;

import java.util.List;

import cn.cjtblog.domain.Sensor;

public interface SensorDAO {
	public void add(Sensor sensor);
    public void update(Sensor sensor);
    public Sensor getById(long id);
	public void deleteById(long id);
	public void delete(Sensor sensor);
	public List<Sensor> getAll();
}
