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

      if (sn.endsWith("[^0-9]")) {
        sn = sn.substring(sn.length() - 10, sn.length() - 1);
      } else {
        sn = sn.substring(sn.length() - 9);
      }

      //      int a =0;
      //      while (sn.substring(0,sn.length()-a).endsWith("[^0-9]")){
      //        a++;
      //      }
      //      sn = sn.substring(sn.length() - 9-a, sn.length() -a);

      rawCameraSet.add(RawCameraData.builder()
              .name(name)
              .firmware(firmware)
              .ipAddress(ip)
              .serialNumber(sn)
              .build());

      //rawCameraSet.add(new RawCameraData(name, firmware, ip, sn));
    }
    return CompletableFuture.completedFuture(rawCameraSet);
  }
}
