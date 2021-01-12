package net.vidux.camhub.discovery;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RawCameraEventListenerTest {

  @Mock RawCameraEventListener listener;

  @Mock RawCameraEventPublisher publisher;

  @Test
  void testListener() {
    RawCameraData rawCameraData =
        new RawCameraData("VDX-OD-6MP-ML-IR-WDR", "94-e1-ac-d1-2a-44", "10.30.0.51", "C28752138");

    publisher.publishRawCameraEvent(rawCameraData);

    RawCameraEvent rawCameraEvent = new RawCameraEvent(publisher, rawCameraData);

    listener.handleRawCameraEvent(rawCameraEvent);

    verify(listener, times(1)).handleRawCameraEvent(rawCameraEvent);
  }
}
