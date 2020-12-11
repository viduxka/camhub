package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class CameraScanViduxHelper implements CameraScan {

  String[] splitRawCameras(String rawCameraLine) {
    return rawCameraLine.split("\\t");
  }

  String getSerialNumber(String productNumber) {
    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (chars[endIndex] > '9' || chars[endIndex] < '0') {
      endIndex--;
    }
    return productNumber.substring(endIndex - 8, endIndex + 1);
  }

  @Override
  public CompletableFuture<Set<RawCameraData>> scanCams() {
    List<String> rawCameraLineList = new ArrayList<>();
    Set<RawCameraData> rawCameraSet = new HashSet<>();
    try {
      ProcessBuilder builder = new ProcessBuilder();
      builder.command("vidux-helper", "system", "findHikvisionIPCameras");
      Process process = builder.start();
      new StreamGobbler(process.getInputStream(), rawCameraLineList::add).run();

      if (!process.waitFor(10, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException("Timeout! The process did not finish in 10 seconds.");
      }

      if (process.waitFor() != 0) {
        throw new IOException("Could not run vidux-helper command properly.");
      }

    } catch (Exception e) {
      return CompletableFuture.failedFuture(e);
    }

    for (String rawCameraLine : rawCameraLineList) {
      String[] cameraInfo = splitRawCameras(rawCameraLine);
      String serialNumber = getSerialNumber(cameraInfo[1]);
      String firmware = cameraInfo[2];
      String ip = cameraInfo[3];
      String name = cameraInfo[13];

      rawCameraSet.add(
          RawCameraData.builder()
              .name(name)
              .firmware(firmware)
              .ipAddress(ip)
              .serialNumber(serialNumber)
              .build());
    }
    return CompletableFuture.completedFuture(rawCameraSet);
  }
}
