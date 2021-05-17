package net.vidux.camhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CamHubApplication {

  public static void main(String[] args) {
    SpringApplication.run(CamHubApplication.class, args);
  }
}
