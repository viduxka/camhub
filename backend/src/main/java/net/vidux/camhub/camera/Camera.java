package net.vidux.camhub.camera;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Builder
public class Camera {
  @Id
  @GeneratedValue(generator = "UUID")
  @Type(type = "uuid-char")
  @Column(nullable = false, updatable = false, columnDefinition = "varchar(36)")
  private UUID id;

  private String name;
  private String ip;
  private String firmware;
  @Setter
  private Instant lastSeen;
  private String password;
  @NaturalId
  private String serialNumber;
}
