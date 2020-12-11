package net.vidux.camhub.discovery;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class CameraScanTest {

  @Test
  void testViduxHelper() {
    Assertions.assertDoesNotThrow(
        () -> {
          CameraScan viduxHelper = new CameraScanViduxHelper();
          Set<RawCameraData> set = viduxHelper.scanCams().get();

          for (RawCameraData cam : set) {
            Assertions.assertNotNull(cam.getName());
            Assertions.assertNotNull(cam.getFirmware());
            Assertions.assertNotNull(cam.getIpAddress());
            Assertions.assertNotNull(cam.getSerialNumber());
            Assertions.assertEquals(9, cam.getSerialNumber().length());
          }
        });
  }

  private static Stream<Arguments> provideFakeCameras() {
    return Stream.of(
        Arguments.of(
            " \tABC123456789\t1234\t10.10.0.1\ta\ta\ta\ta\ta\ta\ta\ta\ta\tVDX-1234",
            new RawCameraData("VDX-1234", "1234", "10.10.0.1", "123456789")));
  }

  @ParameterizedTest
  @MethodSource("provideFakeCameras")
  void testCreateCamera(String fakeCamera, RawCameraData expectedRawCamera) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    RawCameraData createdRawCamera = factory.createRawCameraData(fakeCamera);
    Assertions.assertEquals(expectedRawCamera.getName(), createdRawCamera.getName());
    Assertions.assertEquals(expectedRawCamera.getFirmware(), createdRawCamera.getFirmware());
    Assertions.assertEquals(expectedRawCamera.getIpAddress(), createdRawCamera.getIpAddress());
    Assertions.assertEquals(
        expectedRawCamera.getSerialNumber(), createdRawCamera.getSerialNumber());
  }

  @ParameterizedTest
  @CsvSource({
    "ABCDEC23456789,C23456789",
    "ABCDE123456789,123456789",
    "ABCDEC23456789ABC,C23456789"
  })
  void testGetSerialNumber(String fakeProductNumber, String expectedSerialNumber) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    String extractedSerialNumber = factory.extractSerialNumber(fakeProductNumber);
    Assertions.assertEquals(expectedSerialNumber, extractedSerialNumber);
  }
}
