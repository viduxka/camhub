package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ViduxHelperRunner {
  List<String> callViduxHelper() throws TimeoutException, IOException {
    ProcessBuilder builder = new ProcessBuilder();
    builder.command("vidux-helper", "system", "findHikvisionIPCameras");
    Process process = builder.start();
    List<String> list = new ArrayList<>();
    new StreamGobbler(process.getInputStream(), list::add).run();

    try {
      if (!process.waitFor(10, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException("Timeout! The process did not finish in 10 seconds.");
      }
      if (process.waitFor() != 0) {
        throw new IOException("Could not run vidux-helper command properly.");
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return list;
  }
}
