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

import cn.cjtblog.domain.Temperature;
import cn.cjtblog.service.TemperatureService;
import cn.cjtblog.util.DateTimeUtil;
import cn.cjtblog.util.StringUtil;

@RestController
@RequestMapping("/nodes/{nodeId}")
public class TemperatureController {
	private static Logger logger = LoggerFactory
			.getLogger(TemperatureController.class);
	@Autowired
	private TemperatureService temperatureService;

	public TemperatureService getTemperatureService() {
		return temperatureService;
	}

	public void setTemperatureService(TemperatureService temperatureService) {
		this.temperatureService = temperatureService;
	}

	@RequestMapping(value = "/temperatures",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,  method = RequestMethod.GET)
	public List<Temperature> getTemperaturesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@RequestParam(value="beginTime",required=false) String beginTimeStr,
			@RequestParam(value="endTime",required=false) String endTimeStr) {
		List<Temperature> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = temperatureService.getAllTemperatures(nodeId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {
			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = temperatureService.getTemperatureByTimeRange(nodeId, begin,
				end);
		return result;
	}

	@RequestMapping(value = "/sensors/{sensorId}/temperatures",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,  method = RequestMethod.GET)
	public List<Temperature> getTemperaturesByTimeRange(
			@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam("beginTime") String beginTimeStr,
			@RequestParam("endTime") String endTimeStr) {
		List<Temperature> result = null;
		Date begin = null;
		Date end = null;
		if (StringUtil.isEmptyOrNull(beginTimeStr)
				&& StringUtil.isEmptyOrNull(endTimeStr)) {
			result = temperatureService.getAllTemperatures(nodeId, sensorId);
		} else if (StringUtil.isEmptyOrNull(beginTimeStr)) {

			begin = new Date(0);
			end = DateTimeUtil.format(endTimeStr);
		} else if (StringUtil.isEmptyOrNull(endTimeStr)) {
			begin = DateTimeUtil.format(beginTimeStr);
			end = new Date();
		}
		result = temperatureService.getTemperatureByTimeRange(nodeId, sensorId,
				begin, end);
		return result;
	}

	@RequestMapping(value = "/sensors/{sensorId}/temperatures/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public Temperature getTemperatureById(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId, @PathVariable("id") long id) {
		Temperature result = temperatureService.getTemperature(nodeId,
				sensorId, id);
		return result;

	}

	@RequestMapping(value = "/temperatures/newest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Temperature getNewestTemperature(
			@PathVariable("nodeId") long nodeId) {
		return temperatureService.getNewestTemperature(nodeId);
	}

	@RequestMapping(value = "/sensors/{sensorId}/temperatures", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Temperature addTemperature(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestParam Map<String, Object> fieldMap,
			HttpServletResponse response) {
		logger.info(fieldMap.toString());
		Temperature temperature = temperatureService.addTemperature(nodeId,
				sensorId, fieldMap);
		if (temperature != null) {
			response.setHeader("Location", "nodes/" + nodeId + "/sensors/"
					+ sensorId + "/temperatures/" + temperature.getId());
			return temperature;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(value = "/sensors/{sensorId}/temperatures", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Temperature addTemperature(@PathVariable("nodeId") long nodeId,
			@PathVariable("sensorId") long sensorId,
			@RequestBody Temperature temperature, HttpServletResponse response) {
		logger.info(temperature.getSendTime().toString());
		Temperature result = temperatureService.addTemperature(nodeId,
				sensorId, temperature);
		if (result != null) {
			response.setHeader("Location", "nodes/" + nodeId + "/sensors/"
					+ sensorId + "/temperatures/" + result.getId());
			return temperature;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@RequestMapping(value = "/temperatures/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void deleteTemperature(@PathVariable("id") long id) {
		temperatureService.deleteTemperatureById(id);
	}
}
