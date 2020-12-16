package net.vidux.camhub.discovery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
@Builder
class RawCameraData {
  private @NonNull String name;
  private @NonNull String firmware;
  private @NonNull String ipAddress;
  private @NonNull String serialNumber;
}
