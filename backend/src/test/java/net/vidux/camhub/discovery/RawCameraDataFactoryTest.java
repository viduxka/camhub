package net.vidux.camhub.discovery;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
                "VDX-OD-6MP-ML-IR-WDR", "94-e1-ac-d1-2a-44", "10.30.0.51", "C28752138")));
  }

  @ParameterizedTest
  @MethodSource("provideFakeCameras")
  void testCreateCamera(String fakeCamera, RawCameraData expectedRawCamera) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    RawCameraData createdRawCamera = factory.createRawCameraData(fakeCamera);
    Assertions.assertEquals(
        expectedRawCamera, createdRawCamera, "Expected and created camera are not the same.");
  }

  @Test
  void testExceptionCamera() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new RawCameraDataFactory().createRawCameraData(""),
        "Exception was not thrown.");
  }

  private static Stream<Arguments> provideFakeProductNumbers() {
    return Stream.of(
        Arguments.of("VDX-OD-6MP-ML-IR-WDR20180613AAWRC28752138", "C28752138"),
        Arguments.of("ABCDEC23456789ABC", "C23456789"),
        Arguments.of("23456789B", null),
        Arguments.of("ABC", null),
        Arguments.of("", null));
  }

  @ParameterizedTest
  @MethodSource("provideFakeProductNumbers")
  void testGetSerialNumber(String fakeProductNumber, String expectedSerialNumber) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    String extractedSerialNumber = factory.extractSerialNumber(fakeProductNumber);
    Assertions.assertEquals(
        expectedSerialNumber,
        extractedSerialNumber,
        "Expected and extracted serial number are not the same.");
  }
}
