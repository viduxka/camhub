package net.vidux.camhub.discovery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@Getter
@Builder
class RawCameraData {
  @NonNull String name;
  @NonNull String firmware;
  @NonNull String ipAddress;
  @NonNull String serialNumber;
}
