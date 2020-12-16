package net.vidux.camhub.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class CameraDiscoveryService {

  private final AtomicBoolean isCameraScanTaskRunning = new AtomicBoolean();

  private final RawCameraEventPublisher rawCameraPublisher;

  private final DiscoveryTask discoveryTask;

  @Autowired
  public CameraDiscoveryService(
      RawCameraEventPublisher rawCameraPublisher, DiscoveryTask discoveryTask) {
    this.rawCameraPublisher = rawCameraPublisher;
    this.discoveryTask = discoveryTask;
  }

  public void requestDiscovery() throws CameraDiscoveryException {
    if (!isCameraScanTaskRunning.compareAndSet(false, true)) {
      throw new CameraDiscoveryException("Camera Scan task is already running");
    }
    discover();
  }

  void discover() {
    CompletableFuture<Set<RawCameraData>> cameraScanTask = discoveryTask.discover();
    cameraScanTask.exceptionally(
        th -> {
          log.warn(th.getMessage());
          isCameraScanTaskRunning.set(false);
          return null;
        });
    cameraScanTask.thenAccept(this::publishRawCameraData);
  }

  private void publishRawCameraData(Set<RawCameraData> rawData) {
    for (RawCameraData rawCameraData : rawData) {
      rawCameraPublisher.publishRawCameraEvent(rawCameraData);
    }
    isCameraScanTaskRunning.set(false);
  }
}
