package net.vidux.camhub.discovery;

import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

interface CameraScan {
  @Async
  CompletableFuture<Set<RawCameraData>> cameraScanTask();
}
