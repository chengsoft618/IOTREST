package cn.cjtblog.clientTest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import cn.cjtblog.domain.Node;
import cn.cjtblog.util.BeanUtil;

public class ClientTest {
	public static void main(String[]args){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> fieldMap=new HashMap<>();
        fieldMap.put("name", "node1");
        fieldMap.put("longitude", 120);
        fieldMap.put("latitude", 120);
        Node node=BeanUtil.createEntity(Node.class, fieldMap);
        String url="http://localhost:8080/IOTREST/rest/nodes/node";
        String url2="http://localhost:8080/IOTREST/rest/nodes/";
       System.out.println(restTemplate.getForEntity(url2, Object.class));
      System.out.println(restTemplate.postForEntity(url, node, String.class));
        
        
	}
}
