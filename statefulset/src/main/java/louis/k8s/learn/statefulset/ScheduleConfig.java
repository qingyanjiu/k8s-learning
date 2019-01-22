package louis.k8s.learn.statefulset;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "statefulset.env.servertag", havingValue = "0")
public class ScheduleConfig {
}
