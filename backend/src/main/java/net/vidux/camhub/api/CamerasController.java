package net.vidux.camhub.api;


import net.vidux.camhub.models.Camera;
import net.vidux.camhub.repositories.CameraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/cameras")
@RestController
@Service
public class CamerasController {

    private final CameraRepo cameraRepo;

    @Autowired
    public CamerasController(@Qualifier("InMemoryDB") CameraRepo cameraRepo) {
        this.cameraRepo = cameraRepo;
        cameraRepo.addCamera(new Camera("Alma", "123.123.123.123", "1.0", new Date(1605776141000L)));
        cameraRepo.addCamera(new Camera("Barack", "123.123.123.124", "1.0", new Date(1605776151000L)));
        cameraRepo.addCamera(new Camera("Citrom", "123.123.123.125", "1.0", new Date(1605776161000L)));
    }

    @GetMapping
    public List<Camera> getCameraList(){
        return cameraRepo.getCameras();
    }
}
