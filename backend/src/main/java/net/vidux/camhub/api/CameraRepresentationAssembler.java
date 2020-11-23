package net.vidux.camhub.api;

import lombok.NonNull;
import net.vidux.camhub.models.Camera;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CameraRepresentationAssembler extends RepresentationModelAssemblerSupport<Camera, CameraRepresentation> {

    public CameraRepresentationAssembler() {
        super(CamerasController.class, CameraRepresentation.class);
    }

    @Override
    public CameraRepresentation toModel (@NonNull Camera entity) {
        final CameraRepresentation model =
                CameraRepresentation.builder()
                        .name(entity.getName())
                        .ip(entity.getIp())
                        .firmware(entity.getFirmware())
                        .lastSeen(entity.getLastSeen())
                        .build();

//        Link self = linkTo(getControllerClass()).slash(entity.Class()).withSelfRel();
//        model.add(self);
        return model;
    }
}
