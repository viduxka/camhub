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

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Relation(collectionRelation = "cameras", itemRelation = "camera")
@Builder
public class CameraRepresentation extends RepresentationModel<CameraRepresentation> {
  private String name;
  private String ip;
  private String firmware;
  private Date lastSeen;
}
