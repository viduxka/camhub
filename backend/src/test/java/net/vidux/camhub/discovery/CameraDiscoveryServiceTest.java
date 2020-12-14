package net.vidux.camhub.discovery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CameraDiscoveryServiceTest {

  @Mock CompletableFuture<Set<RawCameraData>> cameraScanTask;

  @Mock RawCameraEventPublisher rawCameraPublisher;

  @Mock CameraScan cameraScan;

  @InjectMocks CameraDiscoveryService cameraDiscoveryService;

  @Test
  void requestDiscoverTestWhenScanIsRunning() {
    when(cameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return false;
            });

    Assertions.assertThrows(
        CameraDiscoveryException.class, cameraDiscoveryService::requestDiscovery);
  }

  @Test
  void requestDiscoverTestWhenNoScanIsRunning() {
    Set<RawCameraData> mockScanResultSet = new HashSet<>();
    RawCameraData cam1 = mock(RawCameraData.class);
    RawCameraData cam2 = mock(RawCameraData.class);
    RawCameraData cam3 = mock(RawCameraData.class);
    mockScanResultSet.add(cam1);
    mockScanResultSet.add(cam2);
    mockScanResultSet.add(cam3);
    when(cameraScan.cameraScanTask())
        .thenReturn(CompletableFuture.completedFuture(mockScanResultSet));
    cameraDiscoveryService.cameraScanTask = null;

    Assertions.assertDoesNotThrow(cameraDiscoveryService::requestDiscovery);
    verify(rawCameraPublisher, times(1)).publishRawCameraEvent(cam1);
    verify(rawCameraPublisher, times(1)).publishRawCameraEvent(cam2);
    verify(rawCameraPublisher, times(1)).publishRawCameraEvent(cam3);
  }

  @Test
  void requestDiscoverTestWhenScanIsDone() {
    Set<RawCameraData> mockScanResultSet = new HashSet<>();
    RawCameraData cam1 = mock(RawCameraData.class);
    RawCameraData cam2 = mock(RawCameraData.class);
    mockScanResultSet.add(cam1);
    mockScanResultSet.add(cam2);
    when(cameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return true;
            });
    when(cameraScan.cameraScanTask())
        .thenReturn(CompletableFuture.completedFuture(mockScanResultSet));

    Assertions.assertDoesNotThrow(cameraDiscoveryService::requestDiscovery);
    verify(rawCameraPublisher, times(1)).publishRawCameraEvent(cam1);
    verify(rawCameraPublisher, times(1)).publishRawCameraEvent(cam2);
  }
}
