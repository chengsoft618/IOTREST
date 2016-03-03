package cn.cjtblog.controller.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Smoke;
import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.SmokeService;
import cn.cjtblog.util.DateTimeUtil;
import cn.cjtblog.util.StringUtil;

@RestController
@RequestMapping("/nodes/{nodeId}")
public class SmokeController {
	private static Logger logger = LoggerFactory
			.getLogger(SensorController.class);
	@Autowired
	private SmokeService smokeService;

	public SmokeService getSmokeService() {
		return smokeService;
	}

	public void setSmokeService(SmokeService smokeService) {
		this.smokeService = smokeService;
	}


	@RequestMapping(value="/smokes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	List<Smoke> getAllSmokesByTimeRange(@PathVariable("nodeId") long nodeId,@RequestParam(value="beginTime",required=false) String beginTimeStr,
			@RequestParam(value="endTime",required=false) String endTimeStr) {
		List<Smoke> result=null;
		Date begin = null;
		Date end = null;
		if(StringUtil.isEmptyOrNull(beginTimeStr)&&StringUtil.isEmptyOrNull(endTimeStr)){
			result=smokeService.getAllSmokes(nodeId);
		}
		else if(StringUtil.isEmptyOrNull(beginTimeStr)){
			begin=new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		}
		else if(StringUtil.isEmptyOrNull(endTimeStr)){
			begin = DateTimeUtil.format(beginTimeStr);
			end=new Date();
		}
		result=smokeService.getSmokesByTimeRange(nodeId, begin, end);
		return result;
	}
	

	@RequestMapping(value = "/sensors/{sensorId}/smokes",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,  method = RequestMethod.GET)
	public List<Smoke> getSmokesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam("beginTime") String beginTimeStr,
			@RequestParam("endTime") String endTimeStr) {
		List<Smoke> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = smokeService.getAllSmokes(nodeId, sensorId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {

			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = smokeService.getSmokesByTimeRange(nodeId, sensorId,
				begin, end);
		return result;
	}
	
	@RequestMapping(value = "/smokes/newest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Smoke getNewestSmoke(
			@PathVariable("nodeId")long nodeId) {
		logger.info("get NewestHumidity");
		return smokeService.getNewestSmoke(nodeId);
	}
	@RequestMapping(value = "/sensors/{sensorId}/smokes/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Smoke getSmoke(
			@PathVariable("nodeId") long nodeId,@PathVariable("sensorId") long sensorId,@PathVariable("id")long id) {
		return smokeService.getSmoke(nodeId,sensorId,id);
	}

	@RequestMapping(value = "/sensors/{sensorId}/smokes",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Smoke addSmoke(@PathVariable("nodeId") long nodeId,@PathVariable("sensorId") long sensorId,
			@RequestParam Map<String,Object> fieldMap,HttpServletResponse response) {
		 Smoke smoke= smokeService.addSmoke(nodeId, sensorId, fieldMap);
		if(smoke!=null)
		{
			response.setHeader("Location", "nodes/"+nodeId+"/sensors/"+sensorId+"/smokes/"+smoke.getId());
			return smoke;
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
	
	
	@RequestMapping(value = "/sensors/{sensorId}/smokes",produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Smoke addSmoke(@PathVariable("nodeId") long nodeId,@PathVariable("sensorId") long sensorId,
			@RequestBody Smoke smoke,HttpServletResponse response) {
		Smoke result=smokeService.addSmoke(nodeId, sensorId, smoke);
		if(result!=null)
		{
			response.setHeader("Location", "nodes/"+nodeId+"/sensors/"+sensorId+"/smokes/"+result.getId());
			return result;
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}


	@RequestMapping(value = "/smokes/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteSmoke(@PathVariable("id") long id) {
		smokeService.deleteSmokeById(id);
	}
	
}
