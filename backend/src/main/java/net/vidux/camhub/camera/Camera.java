package net.vidux.camhub.camera;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
public class Camera {
  @NonNull @Id @GeneratedValue private long id;
  @NonNull private String name;
  @NonNull private String ip;
  @NonNull private String firmware;
  @NonNull private Instant lastSeen;
  @NonNull private String password;
  @NaturalId private String serialNumber;
}
