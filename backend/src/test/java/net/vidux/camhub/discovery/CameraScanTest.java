package net.vidux.camhub.discovery;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CameraScanTest {

  @Test
  void callViduxHelperTest() {
    CameraScan viduxHelper = new CameraScanViduxHelper();
    try {
      Set<RawCameraData> set = viduxHelper.scanCams().get();
      Assertions.assertFalse(set.isEmpty());

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

  @Test
  void slitCameraInfoTest() {
    String fakeCamera = "VDX-1234\t1234\t10.10.0.1\ttest";
    String[] expectedCamera = {"VDX-1234", "1234", "10.10.0.1", "test"};
    CameraScanViduxHelper viduxHelper = new CameraScanViduxHelper();
    String[] splitCamera = viduxHelper.splitRawCameras(fakeCamera);
    for (int i = 0; i < expectedCamera.length; i++) {
      Assertions.assertEquals(expectedCamera[i], splitCamera[i]);
    }
  }

  void getSerialNumberTest(String fakeProductNumber, String expectedSerialNumber) {
    CameraScanViduxHelper viduxHelper = new CameraScanViduxHelper();
    String extractedSerialNumber = viduxHelper.getSerialNumber(fakeProductNumber);
    Assertions.assertEquals(expectedSerialNumber, extractedSerialNumber);
  }

  @Test
  void getSerialNumberTest() {
    getSerialNumberTest("ABCDEC23456789", "C23456789");
    getSerialNumberTest("ABCDE123456789", "123456789");
    getSerialNumberTest("ABCDEC23456789ABC", "C23456789");
  }
}
