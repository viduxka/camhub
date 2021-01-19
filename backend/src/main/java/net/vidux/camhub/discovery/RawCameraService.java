package net.vidux.camhub.discovery;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
class RawCameraService {

  private final CameraFactory cameraFactory;

  private final CameraRepository cameraRepository;

  @Autowired
  public RawCameraService(CameraFactory cameraFactory, CameraRepository cameraRepository) {
    this.cameraFactory = cameraFactory;
    this.cameraRepository = cameraRepository;
  }

  void processRawCameraData(RawCameraData rawCameraData, Instant timestamp) {
    Optional<Camera> optionalStoredCamera = cameraRepository.findBySerialNumber(rawCameraData.getSerialNumber());
    if (optionalStoredCamera.isEmpty()) {
      cameraRepository.save(cameraFactory.createCameraFromRawCameraDataAndTimeStamp(rawCameraData, timestamp));
    } else {
      Camera storedCamera = optionalStoredCamera.get();
      storedCamera.setLastSeen(timestamp);
      cameraRepository.save(storedCamera);
    }
  }
}
