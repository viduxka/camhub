package net.vidux.camhub.camera;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Camera {
  private String name;
  private String ip;
  private String firmware;
  private Date lastSeen;
  private String password;
  @Id
  private String serialNumber;
}
