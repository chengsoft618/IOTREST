package cn.cjtblog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test/vm1")
	public String test1(Map<String, Object> model,HttpServletRequest request){
			model.put("contextPath", request.getServletContext().getContextPath());
			model.put("hello", "hello world");
			return "vm1";
	}
}
