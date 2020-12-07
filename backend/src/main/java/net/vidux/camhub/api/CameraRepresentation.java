package net.vidux.camhub.api;


import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

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
  private String password;
  private String serialNumber;
}
