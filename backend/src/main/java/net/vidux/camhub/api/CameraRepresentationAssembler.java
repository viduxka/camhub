package net.vidux.camhub.api;

import lombok.NonNull;
import net.vidux.camhub.camera.Camera;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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
            .lastSeen(ZonedDateTime.ofInstant(camera.getLastSeen(), ZoneId.systemDefault()))
            .password(camera.getPassword())
            .serialNumber(camera.getSerialNumber())
            .build();

    Link camConfig = Link.of("http://" + camera.getIp(),"camConfig");
    cameraRepresentation.add(camConfig);
    return cameraRepresentation;
  }
}
