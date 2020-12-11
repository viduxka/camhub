package net.vidux.camhub.discovery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DiscoveryServiceTest {

  @Test
  void requestDiscoverTestWhenScanIsRunning() {
    CompletableFuture<Set<RawCameraData>> mockCameraScanTask =
        (CompletableFuture<Set<RawCameraData>>) mock(CompletableFuture.class);
    when(mockCameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return false;
            });

    DiscoveryService discoveryService = new DiscoveryService();

    DiscoveryService mockDiscover = spy(discoveryService);
    doNothing().when(mockDiscover).discover();

    mockDiscover.cameraScanTask = mockCameraScanTask;

    Assertions.assertThrows(DiscoveryException.class, mockDiscover::requestDiscovery);
  }

  @Test
  void requestDiscoverTestWhenNoScanIsRunning() {
    DiscoveryService discoveryService = new DiscoveryService();
    DiscoveryService mockDiscover = spy(discoveryService);
    doNothing().when(mockDiscover).discover();
    discoveryService.cameraScanTask = null;

    Assertions.assertDoesNotThrow(mockDiscover::requestDiscovery);
    verify(mockDiscover, times(1)).discover();
  }

  @Test
  void requestDiscoverTestWhenScanIsDone() {
    CompletableFuture<Set<RawCameraData>> mockCameraScanTask =
        (CompletableFuture<Set<RawCameraData>>) mock(CompletableFuture.class);
    when(mockCameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return true;
            });
    DiscoveryService discoveryService = new DiscoveryService();
    DiscoveryService mockDiscover = spy(discoveryService);
    doNothing().when(mockDiscover).discover();
    mockDiscover.cameraScanTask = mockCameraScanTask;

    Assertions.assertDoesNotThrow(mockDiscover::requestDiscovery);
    verify(mockDiscover, times(1)).discover();
  }
}
