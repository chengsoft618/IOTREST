package cn.cjtblog.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.cjtblog.domain.Node;
import cn.cjtblog.service.NodeService;

@RestController
@RequestMapping("/nodes")
public class NodeController {
	private static Logger logger=LoggerFactory.getLogger(NodeController.class);
	@Autowired
	private NodeService nodeService;
	
	@RequestMapping(method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	List<Node> getAllNodes(){
		List<Node> nodes=nodeService.getAllNodes();
		logger.warn(nodes.toString());
		return nodes;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	Node getNodeById(@PathVariable("id")long id){
		return nodeService.getNodeById(id);
	}
	@RequestMapping(consumes={MediaType.APPLICATION_JSON_UTF8_VALUE}, produces={MediaType.APPLICATION_JSON_UTF8_VALUE}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Node addNode(@RequestBody Node node,HttpServletRequest request,HttpServletResponse response) {
		Node result=nodeService.addNode(node);
		if(result!=null)
		{
			logger.info(node.getDescription());
			response.setHeader("Location", "nodes/"+node.getId());
			return result;
		}
		else
		{
			throw new RuntimeException("post error");
		}	
	}
	
	@RequestMapping(consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces={MediaType.APPLICATION_JSON_UTF8_VALUE}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Node addNodeMap(@RequestParam Map<String,Object> fieldMap,HttpServletResponse response) {
		Node result=nodeService.addNode(fieldMap);
		if(result!=null)
		{
			response.setHeader("Location", "nodes/"+result.getId());
			return result;
		}
		else
		{
			throw new RuntimeException("post error");
		}	
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void updateNode(@PathVariable("id")long id,HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String longitude=request.getParameter("longitude");
		String altitude=request.getParameter("altitude");
		String description=request.getParameter("description");
		Map<String,Object> fieldMap=new HashMap<>();
		fieldMap.put("name", name);
		fieldMap.put("longitude", longitude);
		fieldMap.put("altitude", altitude);
		fieldMap.put("description", description);
		nodeService.updateNode(id,fieldMap);	
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	void deleteNode(@PathVariable("id")long id){
		nodeService.deleteNodeById(id);
	}
	public NodeService getNodeService() {
		return nodeService;
	}
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}
