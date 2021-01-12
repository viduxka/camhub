package net.vidux.camhub.api;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import net.vidux.camhub.camera.Camera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;

public class CameraRepresentationAssemblerTest {

  @Test
  void testAssembler() {
    Camera camera =
        Camera.builder()
            .name("VDX-OD-6MP-ML-IR-WDR")
            .ip("10.30.0.51")
            .firmware("94-e1-ac-d1-2a-44")
            .lastSeen(Instant.ofEpochMilli(1605776141000L))
            .password("ViduxC28752138")
            .serialNumber("C28752138")
            .build();

    CameraRepresentation expectedCameraRepresentation =
        CameraRepresentation.builder()
            .name("VDX-OD-6MP-ML-IR-WDR")
            .ip("10.30.0.51")
            .firmware("94-e1-ac-d1-2a-44")
            .lastSeen(
                ZonedDateTime.ofInstant(
                    Instant.ofEpochMilli(1605776141000L), ZoneId.systemDefault()))
            .password("ViduxC28752138")
            .serialNumber("C28752138")
            .build();

    Link camConfig = Link.of("http://10.30.0.51", "camConfig");
    expectedCameraRepresentation.add(camConfig);

    Assertions.assertEquals(
        expectedCameraRepresentation,
        new CameraRepresentationAssembler().toModel(camera),
        "Expected and created cameraRepresentation are not the same.");
  }
}
