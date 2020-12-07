package net.vidux.camhub.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Camera {
  private final String name;
  private String ip;
  private String firmware;
  private Date lastSeen;
  private String password;
  private String serialNumber;
}
