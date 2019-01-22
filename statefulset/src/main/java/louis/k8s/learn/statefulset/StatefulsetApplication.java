package louis.k8s.learn.statefulset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class StatefulsetApplication {

	Logger logger = LoggerFactory.getLogger(getClass());

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

	@Scheduled(cron = "0/5 * * * * *")
	public void executeCronJob(){
		logger.info("This is node 0, I'm executing cron job, the time is now "+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
	}

}

