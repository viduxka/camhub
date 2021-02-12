package net.vidux.camhub.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Relation(itemRelation = "camera", collectionRelation = "cameras")
@Builder
class CameraRepresentation extends RepresentationModel<CameraRepresentation> {
  private String name;
  private String ip;
  private String firmware;
  private ZonedDateTime lastSeen;
  private String password;
  private String serialNumber;
}
