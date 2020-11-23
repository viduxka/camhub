package net.vidux.camhub.api;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class CameraRepresentation extends RepresentationModel<CameraRepresentation> {
    private String name;
    private String ip;
    private String firmware;
    private Date lastSeen;
}
