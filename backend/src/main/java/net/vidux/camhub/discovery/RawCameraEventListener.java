package net.vidux.camhub.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RawCameraEventListener {

  @Autowired RawCameraDataProcessing rawCameraDataProcessing;

  @Async
  @EventListener
  void handleRawCameraEvent(RawCameraEvent cameraEvent) {
    rawCameraDataProcessing.processRawCameraData(
        cameraEvent.getRawCameraData(), Instant.ofEpochMilli(cameraEvent.getTimestamp()));
  }
}
