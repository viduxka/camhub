package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class ViduxHelperWrapper {
  private static final String[] FIND_HIKVISION_IP_CAMERAS_COMMAND = {
    "vidux-helper", "system", "findHikvisionIPCameras"
  };
  private ApplicationContext applicationContext =
      new AnnotationConfigApplicationContext(TimeOutConfiguration.class);
  private int timeOut = applicationContext.getBean(TimeOutConfiguration.class).getTimeOut();

  List<String> findHikvisionIpCameras() throws TimeoutException, IOException {
    ProcessBuilder builder = new ProcessBuilder();
    builder.command(FIND_HIKVISION_IP_CAMERAS_COMMAND);
    Process process = builder.start();
    List<String> list = new StreamGobbler(process.getInputStream()).call();
    try {
      if (!process.waitFor(timeOut, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException(
            "Timeout! The process did not finish in " + timeOut + " seconds.");
      }
      if (process.waitFor() != 0) {
        throw new IOException(
            "Could not run vidux-helper command properly. Error: " + process.getErrorStream());
      }
    } catch (InterruptedException e) {
      log.warn("Thread interrupted!");
      Thread.currentThread().interrupt();
    }

    return list;
  }
}
