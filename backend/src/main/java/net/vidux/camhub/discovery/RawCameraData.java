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
  String name;
  String firmware;
  String ipAddress;
  String serialNumber;
}
