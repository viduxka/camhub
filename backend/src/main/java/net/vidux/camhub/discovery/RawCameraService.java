package net.vidux.camhub.discovery;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class RawCameraService {

  private CameraFactory cameraFactory;

  private CameraRepository cameraRepository;

  @Autowired
  CameraFactory cameraFactory;

  @Autowired
  CameraRepository cameraRepository;

  void processRawCameraData(RawCameraData rawCameraData, Instant timestamp) {
    Camera storedCamera = cameraRepository.findBySerialNumber(rawCameraData.getSerialNumber());
    if (storedCamera == null) {
      cameraRepository.save(cameraFactory.createCameraFromRawCameraDataAndTimeStamp(rawCameraData, timestamp));
    } else {
      storedCamera.setLastSeen(timestamp);
      cameraRepository.save(storedCamera);
    }
  }
}
