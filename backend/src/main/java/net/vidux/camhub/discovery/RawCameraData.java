package net.vidux.camhub.discovery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
class RawCameraData {
  String name;
  String firmware;
  String ipAddress;
  String serialNumber;
}
