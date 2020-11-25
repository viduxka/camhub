package net.vidux.camhub.api;

import net.vidux.camhub.repositories.CameraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/cameras")
@RestController
@Service
@CrossOrigin
public class CamerasController {

  private final CameraRepo cameraRepo;

  @Autowired private CameraRepresentationAssembler cameraRepresentationAssembler;

  @Autowired
  public CamerasController(@Qualifier("InMemoryDB") CameraRepo cameraRepo) {
    this.cameraRepo = cameraRepo;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<CameraRepresentation>> getCameraList() {
    CollectionModel<CameraRepresentation> cameras =
        cameraRepresentationAssembler.toCollectionModel(cameraRepo.getCameras());
    return new ResponseEntity<>(cameras, HttpStatus.OK);
  }
}
