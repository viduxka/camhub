package net.vidux.camhub.discovery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TimeOutConfiguration {

  @Bean
  int getTimeOut() {
    return 10;
  }
}
