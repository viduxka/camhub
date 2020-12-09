package net.vidux.camhub.discovery;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class RawCameraData {
  String name;
  String firmware;
  String ipAddress;
  String serialNumber;
}
