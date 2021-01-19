package net.vidux.camhub.discovery;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Wrapping the 'vidux-helper system findHikvisionIPCameras' commandline call. */
@Component
@Slf4j
class ViduxHelperWrapper {
  private static final String[] FIND_HIKVISION_IP_CAMERAS_COMMAND = {
    "vidux-helper", "system", "findHikvisionIPCameras"
  };

  private static final int TIMEOUT = 10;

  private final ProcessFactory processFactory;

  @Autowired
  public ViduxHelperWrapper(@NonNull ProcessFactory processFactory) {
    this.processFactory = processFactory;
  }

  /**
   * Create a process which executes the 'vidux-helper system findHikvisionIPCameras' command. The
   * return value are processed to a list of strings corresponding of the rows of the execution
   * result. If the {@code TIMEOUT} ({@value TIMEOUT} sec) is reached then a {@link
   * TimeoutException} is thrown. If the command has a non-zero exit code then an {@link
   * IOException} is thrown with the process error stream as the message of the exception.
   * Interruption during the waiting period causes the shut down of the process and an {@link
   * InterruptedIOException}.
   *
   * <p>Example:
   *
   * <p>'row1' \n 'row2' \n 'row3' -> ['row1', 'row2', 'row3']
   *
   * @return the list of the rows of the vidux-helper command output
   * @throws TimeoutException if the process takes more time as the {@code TIMEOUT}
   * @throws IOException if the process exit with error
   * @throws InterruptedIOException if the process execution is interrupted
   */
  List<String> findHikvisionIpCameras() throws TimeoutException, IOException {
    log.debug("create find hikvision ip cameras process with {}", processFactory);
    Process process = processFactory.create(FIND_HIKVISION_IP_CAMERAS_COMMAND);
    log.debug("process info: {}", process.info());
    try {
      if (!process.waitFor(TIMEOUT, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException(
            "Timeout! The process did not finish in " + TIMEOUT + " seconds.");
      }
      if (process.exitValue() != 0) {
        throw new IOException(
            "Could not run vidux-helper command properly. Error: " + process.getErrorStream());
      }
    } catch (InterruptedException e) {
      process.destroy();
      Thread.currentThread().interrupt();
      throw new InterruptedIOException("process's which should be destroyed pid: " + process.pid());
    }
    return new StreamGobbler(process.getInputStream()).call();
  }
}
