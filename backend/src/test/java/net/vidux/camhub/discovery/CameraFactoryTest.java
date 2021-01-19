package net.vidux.camhub.discovery;

import net.vidux.camhub.camera.Camera;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CameraFactoryTest {

  private static Stream<Arguments> provideRawCameraData() {
    Instant timeStamp = Instant.now();
    return Stream.of(
        Arguments.of(
            RawCameraData.builder()
                .name("VDX-OD-6MP-ML-IR-WDR")
                .firmware("94-e1-ac-d1-2a-44")
                .ipAddress("10.30.0.51")
                .serialNumber("C28752138")
                .build(),
            Camera.builder()
                .name("VDX-OD-6MP-ML-IR-WDR")
                .ip("10.30.0.51")
                .firmware("94-e1-ac-d1-2a-44")
                .lastSeen(timeStamp)
                .password("ViduxC28752138")
                .serialNumber("C28752138")
                .build(),
            timeStamp),
        Arguments.of(
            RawCameraData.builder()
                .name("VDX-PRO-BX-1080P-ABF")
                .firmware("c4-2f-90-27-9b-6a")
                .ipAddress("10.30.0.215")
                .serialNumber("518346210")
                .build(),
            Camera.builder()
                .name("VDX-PRO-BX-1080P-ABF")
                .ip("10.30.0.215")
                .firmware("c4-2f-90-27-9b-6a")
                .lastSeen(timeStamp)
                .password("Vidux518346210")
                .serialNumber("518346210")
                .build(),
            timeStamp),
        Arguments.of(
            RawCameraData.builder()
                .name("VDX-OD-1080p-FX-30M-6")
                .firmware("44-19-b6-4d-75-f1")
                .ipAddress("10.30.0.225")
                .serialNumber("472049297")
                .build(),
            Camera.builder()
                .name("VDX-OD-1080p-FX-30M-6")
                .ip("10.30.0.225")
                .firmware("44-19-b6-4d-75-f1")
                .lastSeen(timeStamp)
                .password("Vidux472049297")
                .serialNumber("472049297")
                .build(),
            timeStamp));
  }

  @ParameterizedTest
  @MethodSource("provideRawCameraData")
  void createCameraFromRawCameraDataAndTimeStampTest(
      RawCameraData rawCameraData, Camera expectedCamera, Instant timeStamp) {
    CameraFactory cameraFactory = new CameraFactory();
    Camera resultCamera =
        cameraFactory.createCameraFromRawCameraDataAndTimeStamp(rawCameraData, timeStamp);
    assertEquals(
        expectedCamera,
        resultCamera,
        "Camera created from rawData does not match the expected result");
  }

  private static Stream<Arguments> provideRawCameraDataWithNull() {
    Instant timeStamp = Instant.now();
    return Stream.of(
        Arguments.of(
            RawCameraData.builder()
                .name("VDX-OD-6MP-ML-IR-WDR")
                .firmware("94-e1-ac-d1-2a-44")
                .ipAddress("10.30.0.51")
                .serialNumber("C28752138")
                .build(),
            null),
        Arguments.of(null, timeStamp));
  }

  @ParameterizedTest
  @MethodSource("provideRawCameraDataWithNull")
  void createCameraFromRawCameraDataAndTimeStampTestNullParam(
      RawCameraData rawCameraData, Instant timeStamp) {
    CameraFactory cameraFactory = new CameraFactory();
    assertThrows(
        NullPointerException.class,
        () -> cameraFactory.createCameraFromRawCameraDataAndTimeStamp(rawCameraData, timeStamp),
        "No NullPointerException was thrown while creating a camera from rawData and one of the params was null");
  }
}
