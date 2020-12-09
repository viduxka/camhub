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
  @Override
  public CompletableFuture<Set<RawCameraData>> scanCams() {
    List<String> list = new ArrayList<>();
    Set<RawCameraData> rawCameraSet = new HashSet<>();
    try {
      ProcessBuilder builder = new ProcessBuilder();
      builder.command("vidux-helper", "system", "findHikvisionIPCameras");
      Process process = builder.start();
      new StreamGobbler(process.getInputStream(), list::add).run();

      if (!process.waitFor(10, TimeUnit.SECONDS)) {
        process.destroy();
        throw new TimeoutException("Timeout");
      }

      if (process.waitFor() != 0) {
        throw new IOException("Cant run command");
      }

    } catch (Exception e) {
      System.err.println(e);
    }

    for (String s : list) {
      String[] array = s.split("\\t");
      String sn = array[1];
      String firmware = array[2];
      String ip = array[3];
      String name = array[13];

      int endIndex = sn.length() - 1;
      final char[] chars = sn.toCharArray();
      while (chars[endIndex] > '9' || chars[endIndex] < '0') {
        endIndex--;
      }
      sn = sn.substring(endIndex - 8, endIndex + 1);

      rawCameraSet.add(
          RawCameraData.builder()
              .name(name)
              .firmware(firmware)
              .ipAddress(ip)
              .serialNumber(sn)
              .build());
    }
    return CompletableFuture.completedFuture(rawCameraSet);
  }
}
