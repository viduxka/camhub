package net.vidux.camhub.api;

import net.vidux.camhub.camera.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

  private final CameraRepository cameraRepo;

  private final CameraRepresentationAssembler cameraRepresentationAssembler;

  @Autowired
  public CamerasController(
      CameraRepository cameraRepo, CameraRepresentationAssembler cameraRepresentationAssembler) {
    this.cameraRepo = cameraRepo;
    this.cameraRepresentationAssembler = cameraRepresentationAssembler;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<CameraRepresentation>> getCameraList() {
    CollectionModel<CameraRepresentation> cameras =
        cameraRepresentationAssembler.toCollectionModel(cameraRepo.findAll());
    return new ResponseEntity<>(cameras, HttpStatus.OK);
  }
}
