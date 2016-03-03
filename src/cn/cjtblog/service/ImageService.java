package cn.cjtblog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.cjtblog.domain.Image;

public interface ImageService {

	Image getImage(long nodeId, long sensorId, long id);

	Image addImage(long nodeId, long sensorId, Map<String, Object> fieldMap,
			byte[] bytes);
	
	Image getNewestImage(long nodeId);


	List<Image> getAllImages(long nodeId);
	List<Image> getAllImages(long nodeId,long sensorId);
	void deleteImageById(long id);

	List<Image> getImagesByTimeRange(long nodeId, Date begin, Date end);
	List<Image> getImagesByTimeRange(long nodeId,long sensorId, Date begin, Date end);


}
