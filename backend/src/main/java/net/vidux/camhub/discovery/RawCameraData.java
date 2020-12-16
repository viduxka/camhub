package net.vidux.camhub.discovery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
class RawCameraData {
  private String name;
  private String firmware;
  private String ipAddress;
  private String serialNumber;
}
