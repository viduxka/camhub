package net.vidux.camhub.camera;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

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
  @Column(columnDefinition = "varchar(36)", nullable = false, updatable = false)
  private UUID id;

  @NonNull private String name;
  @NonNull private String ip;
  @NonNull private String firmware;
  @NonNull @Setter private Instant lastSeen;
  @NonNull private String password;
  @NonNull @NaturalId private String serialNumber;
}
