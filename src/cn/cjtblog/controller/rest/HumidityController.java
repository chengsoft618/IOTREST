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
import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.HumidityService;
import cn.cjtblog.util.DateTimeUtil;
import cn.cjtblog.util.StringUtil;

@RestController
@RequestMapping("/nodes/{nodeId}/")
public class HumidityController {
	private static Logger logger = LoggerFactory
			.getLogger(SensorController.class);
	@Autowired
	private HumidityService humidityService;

	public HumidityService getHumidityService() {
		return humidityService;
	}

	public void setHumidityService(HumidityService humidityService) {
		this.humidityService = humidityService;
	}

	@RequestMapping(path = "/humidities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	List<Humidity> getAllHumiditiesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@RequestParam(value = "beginTime", required = false) String beginTimeStr,
			@RequestParam(value = "endTime", required = false) String endTimeStr) {
		List<Humidity> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = humidityService.getAllHumidities(nodeId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {

			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = humidityService.getHumiditiesByTimeRange(nodeId, begin, end);
		return result;
	}
	
	
	@RequestMapping(value = "/sensors/{sensorId}/humidities",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,  method = RequestMethod.GET)
	public List<Humidity> getHumiditiesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam("beginTime") String beginTimeStr,
			@RequestParam("endTime") String endTimeStr) {
		List<Humidity> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result =humidityService.getAllHumidities(nodeId, sensorId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {

			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = humidityService.getHumiditiesByTimeRange(nodeId, sensorId,
				begin, end);
		return result;
	}

	@RequestMapping(value = "/humidities/newest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Humidity getNewestHumidity(@PathVariable("nodeId") long nodeId) {
		logger.info("get NewestHumidity");
		return humidityService.getNewestHumidity(nodeId);
	}

	@RequestMapping(value = "/sensors/{sensorId}/humidities/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Humidity getHumidity(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId, @PathVariable("id") long id) {
		return humidityService.getHumidity(nodeId, sensorId, id);
	}

	@RequestMapping(value = "/sensors/{sensorId}/humidities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Humidity addHumidity(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam Map<String, Object> fieldMap,
			HttpServletResponse response) {
		Humidity humidity = humidityService.addHumidity(nodeId, sensorId,
				fieldMap);
		if (humidity != null) {
			response.setHeader("Location", "nodes/" + nodeId + "/sensors/"
					+ sensorId + "/humidities/" + humidity.getId());
			return humidity;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(value = "/sensors/{sensorId}/humidities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Humidity addHumidity(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestBody Humidity humidity, HttpServletResponse response) {
		Humidity result = humidityService.addHumidity(nodeId, sensorId,
				humidity);
		if (result != null) {
			response.setHeader("Location", "nodes/" + nodeId + "/sensors/"
					+ sensorId + "/humidities/" + result.getId());
			return result;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(value = "/humidities/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void deleteHumidity(@PathVariable("id") long id) {
		humidityService.deleteHumidityById(id);
	}

}
