package net.katrinka.clinicalrelevance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


@SpringBootApplication
public class ClinicalrelevanceApplication {
	private static Logger log = LoggerFactory.getLogger(ClinicalrelevanceApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ClinicalrelevanceApplication.class);

		application.addListeners((ApplicationListener< ApplicationReadyEvent>) event -> {
			log.info("Application ready");
			JedisConnectionFactory jcf = (JedisConnectionFactory) event.getApplicationContext().getBean("redisConnectionFactory");
			log.info("Redis Host name: {} - port: {}", jcf.getHostName(), jcf.getPort());
			log.info("Pinging Redis: {}", jcf.getConnection().ping());
		});
		application.run(args);
	}
}
