package net.vidux.camhub.discovery;

import lombok.NonNull;
import net.vidux.camhub.camera.Camera;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class CameraFactory {
  Camera createCameraFromRawCameraDataAndTimeStamp(@NonNull RawCameraData rawCameraData, @NonNull Instant timestamp) {
    return Camera.builder()
        .name(rawCameraData.getName())
        .ip(rawCameraData.getIpAddress())
        .firmware(rawCameraData.getFirmware())
        .lastSeen(timestamp)
        .password(generatePasswordFromSerialNumber(rawCameraData.getSerialNumber()))
        .serialNumber(rawCameraData.getSerialNumber())
        .build();
  }
  private String generatePasswordFromSerialNumber(String serialNumber){
    return "Vidux" + serialNumber.substring(serialNumber.length() - 9);
  }
}
