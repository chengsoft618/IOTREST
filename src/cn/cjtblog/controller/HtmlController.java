package cn.cjtblog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.cjtblog.domain.Node;
import cn.cjtblog.domain.Sensor;
import cn.cjtblog.service.NodeService;
import cn.cjtblog.service.SensorService;
import cn.cjtblog.util.SensorType;

@Controller
public class HtmlController {
	private static Logger logger = LoggerFactory
			.getLogger(HtmlController.class);
	@Autowired
	private NodeService nodeService;
	@Autowired
	private SensorService sensorService;

	@RequestMapping("/addSensor.htm")
	String addSensor(
			@RequestParam(value = "nodeId", defaultValue = "1", required = false) long nodeId,
			@RequestParam(value = "sensorType", defaultValue = "temperature", required = false) String sensorType,
			Model model) {
		logger.info("adding Sernsor");
		model.addAttribute("sensorTypes", SensorType.values());
		model.addAttribute("sensorType", sensorType);
		model.addAttribute("nodes", nodeService.getAllNodes());
		model.addAttribute("nodeId", nodeId);
		return "addSensor";
	}

	@RequestMapping("/index.htm")
	String getIndex(Model model) {
		return "index";
	}

	@RequestMapping("/addNode.htm")
	String addNode(Model model) {
		return "addNode";
	}

	@RequestMapping("/addData.htm")
	String addData(Model model) {
		List<Node> nodes = nodeService.getAllNodes();
		List<Sensor> sensors = sensorService.getAllSensors();
		model.addAttribute("nodes", nodes);
		model.addAttribute("sensors", sensors);
		return "addData";
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public SensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}
}
