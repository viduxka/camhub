package net.vidux.camhub.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RawCameraEventListener {

  private final RawCameraService rawCameraService;

  @Autowired
  public RawCameraEventListener(RawCameraService rawCameraService) {
    this.rawCameraService = rawCameraService;
  }

  @Async
  @EventListener
  void handleRawCameraEvent(RawCameraEvent cameraEvent) {
    rawCameraService.processRawCameraData(
        cameraEvent.getRawCameraData(), Instant.ofEpochMilli(cameraEvent.getTimestamp()));
  }
}
