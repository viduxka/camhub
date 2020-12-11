package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

class CameraScanViduxHelper implements CameraScan {

  @Override
  public CompletableFuture<Set<RawCameraData>> scanCams() {
    List<String> rawCameraLineList;
    try {
      rawCameraLineList = new ViduxHelperRunner().callViduxHelper();
    } catch (IOException | TimeoutException e) {
      return CompletableFuture.failedFuture(e);
    }

    Set<RawCameraData> rawCameraSet = new HashSet<>();
    RawCameraDataFactory factory = new RawCameraDataFactory();
    for (String rawCameraLine : rawCameraLineList) {
      rawCameraSet.add(factory.createRawCameraData(rawCameraLine));
    }
    return CompletableFuture.completedFuture(rawCameraSet);
  }
}
