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
        Arguments.of(
            "\tVDX-PRO-BX-1080P-ABF20150513CCWR518346210\tc4-2f-90-27-9b-6a\t10.30.0.215"
                + "\t255.255.255.0\t39169\t8000\t0\t0\tV5.3.0build 151112\tV7.0 build 151019"
                + "\t2020-12-11 00:53:13\t1\tVDX-PRO-BX-1080P-ABF",
            new RawCameraData(
                "VDX-PRO-BX-1080P-ABF", "c4-2f-90-27-9b-6a", "10.30.0.215", "518346210")));
  }

  @ParameterizedTest
  @MethodSource("provideFakeCameras")
  void testCreateCamera(String fakeCamera, RawCameraData expectedRawCamera) {
    RawCameraDataFactory factory = new RawCameraDataFactory();
    RawCameraData createdRawCamera = factory.createRawCameraData(fakeCamera);
    Assertions.assertEquals(
        expectedRawCamera, createdRawCamera, "Expected and created camera are not the same.");
  }

  private static Stream<Arguments> provideIncorrectCameras() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of(
            "\tVDX-OD-1080p-FX-30M-620140710CCWR472049297\t44-19-b6-4d-75-f1\t10.30.0.225"));
  }

  @ParameterizedTest
  @MethodSource("provideIncorrectCameras")
  void testExceptionThrown(String incorrectCamera) {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new RawCameraDataFactory().createRawCameraData(incorrectCamera),
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
