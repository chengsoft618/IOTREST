/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.cjtblog.dao;

import java.util.List;

import cn.cjtblog.domain.Humidity;
import cn.cjtblog.domain.Node;

/**
 *
 * @author cai
 */
public interface NodeDAO {
	public void add(Node node);
    public void update(Node node);
    public Node getById(long id);
	public void deleteById(long id);
	public void delete(Node node);
	public List<Node> getAll();
}
