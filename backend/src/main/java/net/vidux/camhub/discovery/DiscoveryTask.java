package net.vidux.camhub.discovery;

import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

interface DiscoveryTask {
  @Async
  CompletableFuture<Set<RawCameraData>> discover();
}
