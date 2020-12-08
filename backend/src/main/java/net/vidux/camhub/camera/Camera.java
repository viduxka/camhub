package net.vidux.camhub.camera;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Camera {
  @Id
  private long id;
  private String name;
  private String ip;
  private String firmware;
  private Date lastSeen;
  private String password;
  @NaturalId
  private String serialNumber;
}
