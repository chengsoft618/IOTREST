package cn.cjtblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.ImageDAO;
import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.ImageService;
import cn.cjtblog.util.BeanUtil;
import cn.cjtblog.util.FileUtil;
import cn.cjtblog.util.SensorType;

public class ImageServiceImpl extends BaseSensorDataServiceImpl implements
		ImageService {
	private ImageDAO imageDAO;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Image getImage(long nodeId, long sensorId, long id) {
		// TODO Auto-generated method stub
		Node node = nodeDAO.getById(nodeId);

		Sensor sensor=sensorDAO.getById(sensorId);
	
		Image image=imageDAO.getById(id);

		if(node!=null&&sensor!=null&&image!=null){
			if(sensor.getNode().getId()==node.getId()&&image.getSensor().getId()==sensor.getId()){
				return image;
			}
		}
		return null;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Image> getAllImages(long nodeId) {
		// TODO Auto-generated method stub
		return imageDAO.getAll(nodeId);
	}

	@Override
	public List<Image> getImagesByTimeRange(long nodeId, Date begin, Date end) {
		// TODO Auto-generated method stub
		return imageDAO.getByTimeRange(nodeId, begin, end);
	}
	
	@Override
	public Image addImage(long nodeId, long sensorId,
			Map<String, Object> fieldMap, byte[] bytes) {
		Node node = nodeDAO.getById(nodeId);
		Sensor sensor=sensorDAO.getById(sensorId);
		if(node!=null&&sensor!=null&&sensor.getType().equalsIgnoreCase(SensorType.IMAGE.getType())&&sensor.getNode().getId()==node.getId()){
			fieldMap.put("sensor", sensor);
			fieldMap.put("receiveTime", new Date());
			Image image=BeanUtil.createEntity(Image.class, fieldMap);
			String savePath=Image.BASICIMAGEDIR+"node"+node.getId()+"/"+image.getReceiveTime().getTime()+Image.SUFFIX;
			FileUtil.saveFile(savePath, bytes);
			String imageURL=Image.BASICIMAGEURL+"node"+node.getId()+"/"+image.getReceiveTime().getTime()+Image.SUFFIX;
			image.setURL(imageURL);
			imageDAO.add(image);
			return image;
		}
		return null;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Image getNewestImage(long nodeId) {
		// TODO Auto-generated method stub
		return imageDAO.getNewest(nodeId);
	}


	@Override
	public void deleteImageById(long id) {
		imageDAO.deleteById(id);

	}

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}


	@Override
	public List<Image> getAllImages(long nodeId, long sensorId) {
		// TODO Auto-generated method stub
		return imageDAO.getAll(nodeId, sensorId);
	}


	@Override
	public List<Image> getImagesByTimeRange(long nodeId, long sensorId,
			Date begin, Date end) {
		// TODO Auto-generated method stub
		return imageDAO.getByTimeRange(nodeId,sensorId, begin, end);
	}




}
