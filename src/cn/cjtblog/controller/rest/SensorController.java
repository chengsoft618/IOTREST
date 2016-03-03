package cn.cjtblog.controller.rest;

import java.util.Map;
import java.util.Set;

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

import cn.cjtblog.domain.Sensor;
import cn.cjtblog.service.SensorService;

@RestController
@RequestMapping("/nodes/{nodeId}/sensors")
public class SensorController {
	private static Logger logger = LoggerFactory
			.getLogger(SensorController.class);
	@Autowired
	private SensorService sensorService;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	Set<Sensor> getAllSensors(@PathVariable("nodeId") long nodeId) {
		return sensorService.getAllSensorsByNodeId(nodeId);
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	Sensor getSensorById(@PathVariable("id") long id) {
		return sensorService.getSensorById(id);
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Sensor addSensor(@RequestBody Sensor sensor,
			@PathVariable("nodeId") long nodeId, HttpServletResponse response) {
		logger.info("posting");
		Sensor result = sensorService.addSensor(nodeId,sensor);
		if (result != null) {
			response.setHeader("Location", "nodes/"+nodeId+"/sensors/" + result.getId());
			logger.info("posting" + response.getHeader("Location"));
			return result;
		} else {
			throw new RuntimeException("post error");
		}
	}

	@RequestMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Sensor addSensor(@RequestParam Map<String, Object> fieldMap,
			@PathVariable("nodeId") long nodeId, HttpServletResponse response) {
		logger.info("posting");
		Sensor sensor = sensorService.addSensor(nodeId,fieldMap);
		if (sensor != null) {
			response.setHeader("Location", "/nodes/"+nodeId+"/sensors/" + sensor.getId());
			logger.info("posting" + response.getHeader("Location"));
			return sensor;
		} else {
			throw new RuntimeException("post error");
		}
	}
	
	@RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void updateNode(@PathVariable("id") long id,
			@RequestParam Map<String, Object> fieldMap,
			HttpServletResponse response) {

		sensorService.updateSensor(id, fieldMap);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void deleteNode(@PathVariable("id") long id) {
		sensorService.deleteSensorById(id);
	}

	public SensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}
}
