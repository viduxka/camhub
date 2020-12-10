package net.vidux.camhub.discovery;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CameraScanTest {

  @Test
  void callViduxHelper() {
    CameraScan viduxHelper = new CameraScanViduxHelper();
    try {
      Set<RawCameraData> set = viduxHelper.scanCams().get();
      Assertions.assertFalse(set.isEmpty());
      //Assertions.assertEquals(19, set.size());

      for (RawCameraData cam : set) {
        Assertions.assertNotNull(cam.getName());
        Assertions.assertNotNull(cam.getFirmware());
        Assertions.assertNotNull(cam.getIpAddress());
        Assertions.assertNotNull(cam.getSerialNumber());
        Assertions.assertEquals(9, cam.getSerialNumber().length());
      }

    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
