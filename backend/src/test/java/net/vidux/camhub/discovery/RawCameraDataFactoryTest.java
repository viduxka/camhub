package net.vidux.camhub.discovery;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RawCameraDataFactoryTest {

  private static Stream<Arguments> provideFakeCameras() {
    return Stream.of(
        Arguments.of(
            "\tVDX-OD-6MP-ML-IR-WDR20180613AAWRC28752138\t94-e1-ac-d1-2a-44\t10.30.0.51"
                + "\t255.255.255.0\t141927\t8000\t0\t0\tV5.5.51build 180326\tV7.3 build 180205"
                + "\t2020-12-09 16:30:09\t1\tVDX-OD-6MP-ML-IR-WDR\t\t10.30.0.1"
                + "\t::\t::\t64\t95\t0\t1\n",
            new RawCameraData(
                "VDX-OD-6MP-ML-IR-WDR", "94-e1-ac-d1-2a-44", "10.30.0.51", "C28752138")),
        Arguments.of(null, null),
        Arguments.of("", null),
        Arguments.of("VDX\t1234\t10.10.0.1", null));
  }

  @ParameterizedTest
  @MethodSource("provideFakeCameras")
  void testCreateCamera(String fakeCamera, RawCameraData expectedRawCamera) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    RawCameraData createdRawCamera = factory.createRawCameraData(fakeCamera);
    if (expectedRawCamera == null) {
      Assertions.assertNull(createdRawCamera);
    } else {
      Assertions.assertEquals(expectedRawCamera.getName(), createdRawCamera.getName());
      Assertions.assertEquals(expectedRawCamera.getFirmware(), createdRawCamera.getFirmware());
      Assertions.assertEquals(expectedRawCamera.getIpAddress(), createdRawCamera.getIpAddress());
      Assertions.assertEquals(
          expectedRawCamera.getSerialNumber(), createdRawCamera.getSerialNumber());
    }
  }

  private static Stream<Arguments> provideFakeProductNumbers() {
    return Stream.of(
        Arguments.of("ABCDE123456789", "123456789"),
        Arguments.of("ABCDE123456789B", "123456789"),
        Arguments.of("ABCDEC23456789ABC", "C23456789"),
        Arguments.of("ABC", null),
        Arguments.of("", null),
        Arguments.of(null, null));
  }

  @ParameterizedTest
  @MethodSource("provideFakeProductNumbers")
  void testGetSerialNumber(String fakeProductNumber, String expectedSerialNumber) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    String extractedSerialNumber = factory.extractSerialNumber(fakeProductNumber);
    Assertions.assertEquals(expectedSerialNumber, extractedSerialNumber);
  }
}
