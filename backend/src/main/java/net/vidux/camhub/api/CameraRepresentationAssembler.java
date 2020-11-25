package net.vidux.camhub.api;

import lombok.NonNull;
import net.vidux.camhub.models.Camera;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CameraRepresentationAssembler
    extends RepresentationModelAssemblerSupport<Camera, CameraRepresentation> {

  public CameraRepresentationAssembler() {
    super(CamerasController.class, CameraRepresentation.class);
  }

  @Override
  public CameraRepresentation toModel(@NonNull Camera camera) {
    final CameraRepresentation cameraRepresentation =
        CameraRepresentation.builder()
            .name(camera.getName())
            .ip(camera.getIp())
            .firmware(camera.getFirmware())
            .lastSeen(camera.getLastSeen())
            .build();

    //        Link self = linkTo(getControllerClass()).slash(entity.Class()).withSelfRel();
    //        cameraRepresentation.add(self);
    return cameraRepresentation;
  }
}
