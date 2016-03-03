package cn.cjtblog.dao;


import java.util.Date;
import java.util.List;

import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Temperature;

public interface ImageDAO {
	public void add(Image image);
    public void update(Image image);
    public Image getById(long id);
	public void deleteById(long id);
	public void delete(Image image);
	public List<Image> getAll();
	public List<Image> getAll(long nodeId);
	public List<Image> getAll(long nodeId, long sensorId);
	
	public List<Image> getByTimeRange(long nodeId,Date begin, Date end);
	public List<Image> getByTimeRange(long nodeId,long sensorId,Date begin, Date end);
	public Image getNewest(long nodeId, long sensorId);
	public Image getNewest(long nodeId);
	public Image getNewest();
}
