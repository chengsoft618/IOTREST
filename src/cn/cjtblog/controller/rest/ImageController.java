package cn.cjtblog.controller.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.cjtblog.domain.Image;
import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.ImageService;
import cn.cjtblog.util.DateTimeUtil;
import cn.cjtblog.util.StringUtil;

@RestController
@RequestMapping("/nodes/{nodeId}")
public class ImageController {
	private static Logger logger=LoggerFactory.getLogger(ImageController.class);
	@Autowired
	private ImageService imageService;

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/images", method = RequestMethod.GET)
	public List<Image> getImagesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@RequestParam(value="beginTime",required=false) String beginTimeStr,
			@RequestParam(value="endTime",required=false) String endTimeStr) {
		List<Image> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = imageService.getAllImages(nodeId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {
			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = imageService.getImagesByTimeRange(nodeId, begin,
				end);
		return result;
	}
	
	@RequestMapping(value = "/sensors/{sensorId}/images",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public List<Image> getImagesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam(value="beginTime",required=false) String beginTimeStr,
			@RequestParam(value="endTime",required=false) String endTimeStr) {
		List<Image> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = imageService.getAllImages(nodeId, sensorId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {

			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = imageService.getImagesByTimeRange(nodeId, sensorId,
				begin, end);
		return result;
	}
	
	@RequestMapping(value = "/sensors/{sensorId}/images/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Image getImageById(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId, @PathVariable("id") long id) {
		Image result = imageService.getImage(nodeId,
				sensorId, id);
		return result;

	}
	
	
	@RequestMapping(value = "/images/newest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Image getNewestImage(
			@PathVariable("nodeId") long nodeId) {
		Image image = imageService.getNewestImage(nodeId);
		return image;
	}

	@RequestMapping(value = "/sensors/{sensorId}/images",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Image addImage(@PathVariable("nodeId")long nodeId, @PathVariable("sensorId")long sensorId,
			@RequestParam Map<String,Object> fieldMap,@RequestParam(value = "file", required = false) MultipartFile imageFile,HttpServletResponse response) throws IOException {
		logger.info(fieldMap.toString());
		logger.info(imageFile.toString());
		byte[] bytes=imageFile.getBytes();
		
		Image image=imageService.addImage(nodeId, sensorId, fieldMap,bytes);
	   if(image!=null)
	   {
			response.setHeader("Location", "nodes/" + nodeId + "/sensors/"
					+ sensorId + "/images/" + image.getId());
			return image;
	   }
	   else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
	   }

	}


	@RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void deleteImage(@PathVariable("id") long id) {
		imageService.deleteImageById(id);
	}
}
