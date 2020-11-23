package net.vidux.camhub.api;


import net.vidux.camhub.models.Camera;
import net.vidux.camhub.repositories.CameraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/api/v1/cameras")
@RestController
@Service
public class CamerasController {

    private final CameraRepo cameraRepo;

    @Autowired
    private CameraRepresentationAssembler cameraRepresentationAssembler;

    @Autowired
    public CamerasController(@Qualifier("InMemoryDB") CameraRepo cameraRepo) {
        this.cameraRepo = cameraRepo;
        cameraRepo.addCamera(new Camera("Alma", "123.123.123.123", "1.0", new Date(1605776141000L)));
        cameraRepo.addCamera(new Camera("Barack", "123.123.123.124", "1.0", new Date(1605776151000L)));
        cameraRepo.addCamera(new Camera("Citrom", "123.123.123.125", "1.0", new Date(1605776161000L)));
    }

    @GetMapping
    public HttpEntity<CameraRepresentation> getCameraList(){
//        CameraRepresentation camera = cameraRepresentationAssembler.toModel((Camera)cameraRepo.getCameras().toArray()[0]);
//        camera.add(linkTo(methodOn(CamerasController.class).getCameraList()).withSelfRel());
//        return new ResponseEntity<>(camera, HttpStatus.OK);

        CollectionModel<CameraRepresentation> cameras = cameraRepresentationAssembler.toCollectionModel(cameraRepo.getCameras());
        return new ResponseEntity(cameras, HttpStatus.OK);
    }
}
