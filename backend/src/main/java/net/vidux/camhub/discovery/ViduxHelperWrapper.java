package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.springframework.stereotype.Component;

@Component
class ViduxHelperWrapper {
  private static final String[] FIND_HIKVISION_IP_CAMERAS_COMMAND = {
    "vidux-helper", "system", "findHikvisionIPCameras"
  };
  private static final int TIME_OUT = 10;

  List<String> findHikvisionIpCameras() throws TimeoutException, IOException {
    ProcessBuilder builder = new ProcessBuilder();
    builder.command(FIND_HIKVISION_IP_CAMERAS_COMMAND);
    Process process = builder.start();
    List<String> list = new StreamGobbler(process.getInputStream()).call();

    try {
      if (!process.waitFor(TIME_OUT, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException("Timeout! The process did not finish in 10 seconds.");
      }
      if (process.waitFor() != 0) {
        throw new IOException(
            "Could not run vidux-helper command properly. Error: " + process.getErrorStream());
      }
    } catch (InterruptedException e) {
      new LogRecord(Level.WARNING, "Thread interrupted!");
      Thread.currentThread().interrupt();
    }

    return list;
  }
}
