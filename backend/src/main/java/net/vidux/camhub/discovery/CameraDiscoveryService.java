package net.vidux.camhub.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class CameraDiscoveryService {
  protected static Logger log = LoggerFactory.getLogger(CameraDiscoveryService.class.getName());

  CompletableFuture<Set<RawCameraData>> cameraScanTask;

  @Autowired private RawCameraEventPublisher rawCameraPublisher;

  @Autowired private CameraScan cameraScan;

  public void requestDiscovery() throws DiscoveryException {
    if (cameraScanTask != null && !cameraScanTask.isDone()) {
      throw new DiscoveryException("Camera Scan task is already running");
    }
    discover();
  }

  void discover() {
    cameraScanTask = cameraScan.cameraScanTask();
    cameraScanTask.thenAccept(this::publishRawCameraData);
    cameraScanTask.exceptionally(
        th -> {
          log.warn(th.getMessage());
          return null;
        });
  }

  private void publishRawCameraData(Set<RawCameraData> rawData) {
    for (RawCameraData rawCameraData : rawData) {
      rawCameraPublisher.publishRawCameraEvent(rawCameraData);
    }
  }
}
