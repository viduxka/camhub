package net.vidux.camhub.discovery;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
class RawCameraEvent extends ApplicationEvent {
  private transient RawCameraData rawCameraData;

  public RawCameraEvent(Object source, RawCameraData rawCameraData) {
    super(source);
    this.rawCameraData = rawCameraData;
  }
}
