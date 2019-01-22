package louis.k8s.learn.statefulset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class StatefulsetApplication {

	@Value("${statefulset.env.servertag}")
	private String serverTag;

	public static void main(String[] args) {
		SpringApplication.run(StatefulsetApplication.class, args);
	}

	@ResponseBody
	@RequestMapping("serverName")
	public String serverName(HttpServletRequest request) {
		return request.getServerName();
	}

	@ResponseBody
	@RequestMapping("serverTag")
	public String serverTag(HttpServletRequest request) {
		return serverTag;
	}

}

