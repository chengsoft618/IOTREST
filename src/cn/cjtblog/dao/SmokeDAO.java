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
import cn.cjtblog.domain.Smoke;
import cn.cjtblog.domain.Temperature;



/**
 *
 * @author cai
 */
public interface SmokeDAO {
	public void add(Smoke smoke);
    public void update(Smoke smoke);
    public Smoke getById(long id);
	public void deleteById(long id);
	public void delete(Smoke smoke);
	public List<Smoke> getAll();
	public List<Smoke> getAll(long nodeId);
	public List<Smoke> getAll(long nodeId, long sensorId);
	
	public List<Smoke> getByTimeRange(long nodeId,Date begin, Date end);
	public List<Smoke> getByTimeRange(long nodeId,long sensorId,Date begin, Date end);
	public Smoke getNewest(long nodeId);
	public Smoke getNewest(long nodeId, long sensorId);
	public Smoke getNewest();
}
