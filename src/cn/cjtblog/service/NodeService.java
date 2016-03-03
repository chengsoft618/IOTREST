package cn.cjtblog.service;

import java.util.List;
import java.util.Map;

import cn.cjtblog.domain.Node;

public interface NodeService {
	public Node addNode(Node node);
	public Node addNode(Map<String,Object> fieldMap);
    public void updateNode(long id,Map<String,Object> fieldMap);
    public Node getNodeById(long id);
	public void deleteNodeById(long id);
	public List<Node> getAllNodes();
}
