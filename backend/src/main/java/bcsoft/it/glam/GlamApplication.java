package bcsoft.it.glam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GlamApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlamApplication.class, args);
	}

}
