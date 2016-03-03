package cn.cjtblog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cjtblog.dao.NodeDAO;
import cn.cjtblog.domain.Node;
import cn.cjtblog.service.NodeService;
import cn.cjtblog.util.BeanUtil;
import cn.cjtblog.util.FileUtil;

@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class NodeServiceImpl implements NodeService {
	private NodeDAO nodeDAO;
	@Override
	public Node addNode(Node node) {
		// TODO Auto-generated method stub
		nodeDAO.add(node);
		FileUtil.makeDir("node"+node.getId());
		return node;
	}
	@Override
	public Node addNode(Map<String, Object> fieldMap) {
		// TODO Auto-generated method stub
		Node node=BeanUtil.createEntity(Node.class, fieldMap);
		nodeDAO.add(node);
		FileUtil.makeDir("node"+node.getId());
		return node;
	}
	@Override
	public void updateNode(long id,Map<String, Object> fieldMap) {
		// TODO Auto-generated method stub
		Node node=nodeDAO.getById(id);
		if(node!=null){
			BeanUtil.updateEntity(node, fieldMap);
			nodeDAO.update(node);
		}

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Node getNodeById(long id) {
		// TODO Auto-generated method stub
		return nodeDAO.getById(id);
	}

	@Override
	public void deleteNodeById(long id) {
		// TODO Auto-generated method stub
		nodeDAO.deleteById(id);
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Node> getAllNodes() {
		// TODO Auto-generated method stub
		return nodeDAO.getAll();
	}
	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}
	public void setNodeDAO(NodeDAO nodeDAO) {
		this.nodeDAO = nodeDAO;
	}



}
