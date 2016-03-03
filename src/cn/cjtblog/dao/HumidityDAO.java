/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.cjtblog.dao;

import java.util.Date;
import java.util.List;

import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Smoke;


public interface HumidityDAO {
	public void add(Humidity humidity);
    public void update(Humidity humidity);
    public Humidity getById(long id);
	public void deleteById(long id);
	public void delete(Humidity humidity);
	public List<Humidity> getAll();
	public List<Humidity> getAll(long nodeId);
	public List<Humidity> getAll(long nodeId, long sensorId);
	public List<Humidity> getByTimeRange(long nodeId,Date begin, Date end);
	public List<Humidity> getByTimeRange(long nodeId,long sensorId,Date begin, Date end);
	public Humidity getNewest(long nodeId, long sensorId);
	public Humidity getNewest(long nodeId);
	public Humidity getNewest();
}
