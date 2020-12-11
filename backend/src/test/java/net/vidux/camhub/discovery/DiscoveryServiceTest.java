package net.vidux.camhub.discovery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscoveryServiceTest {

  @Mock CompletableFuture<Set<RawCameraData>> cameraScanTask;

  @InjectMocks @Spy DiscoveryService spiedDiscover;

  @Test
  void requestDiscoverTestWhenScanIsRunning() {
    when(cameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return false;
            });

    Assertions.assertThrows(DiscoveryException.class, spiedDiscover::requestDiscovery);
  }

  @Test
  void requestDiscoverTestWhenNoScanIsRunning() {
    doNothing().when(spiedDiscover).discover();
    spiedDiscover.cameraScanTask = null;

    Assertions.assertDoesNotThrow(spiedDiscover::requestDiscovery);
    verify(spiedDiscover, times(1)).discover();
  }

  @Test
  void requestDiscoverTestWhenScanIsDone() {
    when(cameraScanTask.isDone())
        .thenAnswer(
            invocation -> {
              return true;
            });
    doNothing().when(spiedDiscover).discover();

    Assertions.assertDoesNotThrow(spiedDiscover::requestDiscovery);
    verify(spiedDiscover, times(1)).discover();
  }
}
