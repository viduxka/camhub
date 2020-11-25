package net.vidux.camhub;

import net.vidux.camhub.models.Camera;
import net.vidux.camhub.repositories.CameraRepoInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
class InMemoryDBTest {

  private CameraRepoInMemory cameraRepoInMemory;

  @BeforeEach
  void setUpDB() {
    this.cameraRepoInMemory = new CameraRepoInMemory();
  }

  @Test
  void addCameraTest() {
    Camera camera = new Camera("Alma", "123.123.123.123", "1.0", new Date(1605776141000L));
    assertEquals(0, cameraRepoInMemory.getCameras().size(), "Initial DB is not empty");
    assertEquals(0, cameraRepoInMemory.addCamera(null), "Adding null returns ok");
    assertEquals(1, cameraRepoInMemory.addCamera(camera), "Adding camera returns error");
    assertEquals(
        camera, cameraRepoInMemory.getCameras().get(0), "DB does not contain the added camera");
  }
}
